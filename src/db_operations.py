from src import app, db
from src.models import User, Student, Parent, Organization
from src.models import User_sessions, pwd_reset_hash
from src.utils import hash, send_mail, generate_username, generate_access_token
from src.form_verify import verify_basic_form, verify_student_form, verify_parent_form

import datetime

def verify_login_session(session, login_time):
    username = session.get("username")
    access_code = session.get("access_token")
    with app.app_context():
        user_exists = db.session.query(User.query.filter_by(user_id = username).exists()).scalar()
        if user_exists:
            user_session = User_sessions.query.filter_by(user_id = username).all()
            user_session = user_session[-1]
            if user_session.user_id == username and user_session.access_token == access_code:
                if user_session.expire_at > login_time:
                    return {"message" : "login success"}
                else:
                    print(str(login_time))
                    print(str(user_session.expire_at))
                    return {"message" : "session expired"}
    return {"message" : "login unsuccessful"}

def login_user(user_data, login_time):
    username = user_data.get("username")
    password = hash(user_data.get("password"))
    with app.app_context():
        user = User.query.filter_by(user_id = username).first()
        if user:
            if user.password == password:
                access_token = generate_access_token(username, login_time)
                time_change = datetime.timedelta(hours=240) # 10 days
                user_session = User_sessions(user_id = username, access_token = access_token, expire_at = login_time + time_change)
                db.session.add(user_session)
                db.session.commit()
                return ({"message" : "login success"}, access_token)
            return ({"message" : "wrong password"}, None)
        return ({"message" : "user does not exist"}, None)
    
def register_user(user_data, login_time):

    user_type = user_data.get("user_type")
    email = user_data.get("email")
    phone = user_data.get("phone")
    profile_pic = user_data.get("profile-pic")
    password = hash(user_data.get("password"))

    # check_for_null = [user_type, email, phone, profile_pic, password, confirm_password]

    # if any_null(check_for_null):
    #     return {"message" : "Enter all the details"}
    # else:
    basic_verified_msg = verify_basic_form(email, phone)
    if basic_verified_msg == True:
        username = generate_username(user_type, phone)
        user = User(user_id = username, user_type = user_type, email = email, phone = phone, profile_pic = profile_pic, password = password)
        access_token = generate_access_token(username, login_time)
        time_change = datetime.timedelta(hours=240) # 10 days
        user_session = User_sessions(user_id = username, access_token = access_token, expire_at = login_time + time_change)
    else:
        return ({"message" : basic_verified_msg}, None)

    if user_type == "student":

        # basic details
        name = user_data.get("name")
        password = hash(password)
        dob = user_data.get("dob")
        # parent details
        parent_email = user_data.get("parent_email")
        parent_phone = user_data.get("parent_phone")

        parent_details = {
            "email" : parent_email,
            "phone" : parent_phone
        }

        student_form_verified_msg = verify_student_form(dob, parent_details)

        if student_form_verified_msg == True:

            # Creating parent user 

            parent_username = generate_username("parent", phone)
            parent_pwd = hash("parent-pwd")
            parent_user = User(user_id = parent_username, user_type = "parent", email = parent_email, phone = parent_phone, password = parent_pwd)
            
            parent = Parent(user_id = parent_username)
            
            # Creating Student User

            student = Student(user_id = username, name = name, dob = dob, parent_id = parent_username)

            # commiting users to database

            with app.app_context():

                user_email_exists = db.session.query(User.query.filter_by(email = email, user_type = "student").exists()).scalar()
                user_phone_exists = db.session.query(User.query.filter_by(phone = phone, user_type = "student").exists()).scalar()
                if user_email_exists or user_phone_exists:
                    return ({"message" : "user already exists"}, None)
                
                try:
                    db.session.add(user)
                    db.session.add(student)
                    db.session.commit()
                except:
                    return ({"message" : "user already exists"}, None)
            
            with app.app_context():
                try:
                    db.session.add(user_session)
                    db.session.add(parent_user)
                    db.session.add(parent)
                    db.session.commit()
                except:
                    return ({"message" : "parent user already exists"}, None)
            session_data = {"username" : username, "access_token" : access_token}
            return ({"message" : "registered"}, session_data)
        
        else:
            return ({"message" : student_form_verified_msg}, None)
    
    elif user_type == "parent":

        # basic details
        username = generate_username(user_type, phone)
        password = hash(password)
        parent = Parent(user_id = username)
        
        with app.app_context():

            user_email_exists = db.session.query(User.query.filter_by(email = email, user_type = "parent").exists()).scalar()
            user_phone_exists = db.session.query(User.query.filter_by(phone = phone, user_type = "parent").exists()).scalar()
            if user_email_exists or user_phone_exists:
                return ({"message" : "user already exists"}, None)
            
            try:
                db.session.add(user)
                db.session.add(parent)
                db.session.commit()
                session_data = {"username" : username, "access_token" : access_token}
                return ({"message" : "registered"}, session_data)
            except:
                return ({"message" : "user already exists"}, None)

    elif user_type == "organisation" :

        # basic details
        name = user_data.get("name")
        username = generate_username(user_type, phone)
        password = hash(password)

        organisation = Organization(user_id = username, name = name)
        
        with app.app_context():

            user_email_exists = db.session.query(User.query.filter_by(email = email, user_type = "oraganisation").exists()).scalar()
            user_phone_exists = db.session.query(User.query.filter_by(phone = phone, user_type = "oraganisation").exists()).scalar()
            if user_email_exists or user_phone_exists:
                return ({"message" : "user already exists"}, None)
            
            try:
                db.session.add(user)
                db.session.add(organisation)
                db.session.commit()
                session_data = {"username" : username, "access_token" : access_token}
                return ({"message" : "registered"}, session_data)
            except:
                return ({"message" : "user already exists"}, None)
            
def reset_pwd(unique_id, pwd):

    with app.app_context():

        username = pwd_reset_hash.query.filter_by(hash_id = unique_id).first().user_id
        user = User.query.filter_by(user_id = username).first()
        user.password = hash(pwd)
        db.session.commit()