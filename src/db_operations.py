from src import app, db
from src.models import User, Student, Parent, Organization
from src.utils import hash, any_null, send_mail
from src.form_verify import verify_basic_form, verify_student_form, verify_parent_form

def register_user(user_data):

    user_type = user_data.get("user_type")
    email = user_data.get("email")
    phone = user_data.get("phone")
    profile_pic = user_data.get("profile-pic")
    password = hash(user_data.get("password"))
    confirm_password = hash(user_data.get("password"))

    check_for_null = [user_type, email, phone, profile_pic, password, confirm_password]

    if any_null(check_for_null):
        return {"message" : "Enter all the details"}
    else:
        basic_verified_msg = verify_basic_form(email, phone, password, confirm_password)
        if basic_verified_msg == True:
            user_id = hash(email)
            user = User(user_id = user_id, user_type = user_type, email = email, phone = phone, profile_pic = profile_pic)
        else:
            return {"message" : basic_verified_msg}

    if user_type == "student":

        # basic details
        name = user_data.get("name")
        password = hash(password)
        dob = user_data.get("dob")
        # parent details
        parent_email = user_data.get("parent_email")
        parent_phone = user_data.get("parent_email")
        # other details
        special_needs = user_data.get("special_needs")
        other_remarks = user_data.get("other_remarks")

        parent_details = {
            "email" : parent_email,
            "phone" : parent_phone
        }

        student_form_verified_msg = verify_student_form(dob, parent_details)

        if student_form_verified_msg == True:

            # Creating parent user 

            parent_user_id = hash(parent_email)
            parent_pwd = hash("parent-pwd")
            parent_user = User(user_id = parent_user_id, user_type = "parent", email = parent_email, phone = parent_phone)
            
            parent = Parent(user_id = parent_user_id, password = parent_pwd)
            
            # Creating Student User

            student = Student(user_id = user_id, name = name, dob = dob, parent_id = parent_user_id, password = password)

            # commiting users to database
            
            with app.app_context():
                db.session.add(user)
                db.session.add(parent_user)
                db.session.add(parent)
                db.session.add(student)
                db.session.commit()
        else:
            return {"message" : student_form_verified_msg}
    
    elif user_type == "parent":

        # basic details
        user_id = hash(email)
        password = hash(password)
        parent = Parent(user_id = user_id, password = password)
        
        with app.app_context():
            db.session.add(user)
            db.session.add(parent)
            db.session.commit()

    elif user_type == "organisation" :

        # basic details
        name = user_data.get("name")
        user_id = hash(email)
        password = hash(password)

        organisation = Organization(user_id = user_id, name = name, password = password)
        
        with app.app_context():
            db.session.add(user)
            db.session.add(organisation)
            db.session.commit()
    