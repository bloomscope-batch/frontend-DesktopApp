from src import app, db
from src.models import User, Student, Parent, Organization
from src.utils import hash, send_mail, generate_username
from src.form_verify import verify_basic_form, verify_student_form, verify_parent_form

from datetime import datetime

def login_user(user_data, login_after_registeration = False):
    username = hash(user_data.get("username"))
    password = hash(user_data.get("password"))
    user = User.query.filterby(user_id = username).fisrt()
    print(user)
    
def register_user(user_data):

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
        username = generate_username(user_type, email, phone)
        user = User(user_id = username, user_type = user_type, email = email, phone = phone, profile_pic = profile_pic)
    else:
        return {"message" : basic_verified_msg}

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

            parent_username = "Parent" + generate_username(user_type, email, phone)
            parent_pwd = hash("parent-pwd")
            parent_user = User(user_id = parent_username, user_type = "parent", email = parent_email, phone = parent_phone)
            
            parent = Parent(user_id = parent_username, password = parent_pwd)
            
            # Creating Student User

            student = Student(user_id = username, name = name, dob = dob, parent_id = parent_username, password = password)

            # commiting users to database

            with app.app_context():
                try:
                    db.session.add(user)
                    db.session.add(student)
                    db.session.commit()
                except:
                    return {"message" : "user already exists"}
            
            with app.app_context():
                try:
                    db.session.add(parent_user)
                    db.session.add(parent)
                    db.session.commit()
                except:
                    return {"message" : "parent user already exists"}
            
            return {"message" : "registered"}
        
        else:
            return {"message" : student_form_verified_msg}
    
    elif user_type == "parent":

        # basic details
        username = "Parent" + generate_username(user_type, email, phone)
        password = hash(password)
        parent = Parent(user_id = username, password = password)
        
        with app.app_context():
            try:
                db.session.add(user)
                db.session.add(parent)
                db.session.commit()
                return {"message" : "registered"}
            except:
                return {"message" : "user already exists"}

    elif user_type == "organisation" :

        # basic details
        name = user_data.get("name")
        username = generate_username(user_type, email, phone)
        password = hash(password)

        organisation = Organization(user_id = username, name = name, password = password)
        
        with app.app_context():
            try:
                db.session.add(user)
                db.session.add(organisation)
                db.session.commit()
                return {"message" : "registered"}
            except:
                return {"message" : "user already exists"}

