from src import app, db
from src.models import User
from src.utils import hash, any_null, send_mail
from src.form_verify import verify_basic_form, verify_student_form, verify_parent_form

def register_user(user_data):

    user_type = user_data.get("user_type")
    email = user_data.get("email")
    name = user_data.get("name")
    phone = user_data.get("phone")
    password = hash(user_data.get("password"))
    confirm_password = hash(user_data.get("password"))

    check_for_null = [user_type, email, name, phone, password, confirm_password]

    if any_null(check_for_null):
        return {"message" : "Enter all the details"}
    else:
        basic_verified_msg = verify_basic_form(email, phone, password, confirm_password)
        if basic_verified_msg == True:
            pass
        else:
            return {"message" : basic_verified_msg}

    if user_type == "student":

        # basic details
        user_id = hash(email)
        password = hash(password)
        dob = user_data.get("dob")
        # father details
        f_email = user_data.get("father_email")
        f_name = user_data.get("father_name")
        f_phone = user_data.get("father_phone")
        # mother details
        m_email = user_data.get("mother_email")
        m_name = user_data.get("mother_name")
        m_phone = user_data.get("mother_phone")
        # other details
        special_needs = user_data.get("special_needs")
        other_remarks = user_data.get("other_remarks")

        parent_details = {
            "father" : [f_email, f_name, f_phone],
            "mother" : [m_email, m_email, m_phone]
        }

        student_form_verified_msg = verify_student_form(dob, parent_details)

        if student_form_verified_msg == True:
            student = User(user_id = user_id, email = email, user_type = user_type, 
                    password = password, name = name, phone = phone, dob = dob,
                    father_email = f_email, father_name = f_name, father_phone = f_phone,
                    mother_email = m_email, mother_name = m_name, mother_phone = m_phone,
                    special_needs = special_needs, other_remarks = other_remarks
            )
            
            with app.app_context():
                db.session.add(student)
                db.session.commit()
        else:
            return {"message" : student_form_verified_msg}
    
    elif user_type == "parent":

        # basic details
        user_id = hash(email)
        password = hash(password)

        self_relation = user_data.get("self_relation")
        spouse_relation = "mother"
        if self_relation == "mother":
            spouse_relation = "father"
    
        spouse_name = user_data.get(spouse_name)
        spouse_email = user_data.get(spouse_email)
        spouse_phone = user_data.get(spouse_phone)

        parent = User(user_id = user_id, email = email, user_type = user_type, 
                    password = password, name = name, phone = phone,
                    self_relation = self_relation, spouse_relation = spouse_relation,
                    spouse_name = spouse_name, spouse_email = spouse_email, spouse_phone = spouse_phone)
        
        with app.app_context():
            db.session.add(parent)
            db.session.commit()

    elif user_type == "organisation" :

        # basic details
        user_id = hash(email)
        password = hash(password)

        organisation = User(user_id = user_id, email = email, user_type = user_type, 
                    password = password, name = name, phone = phone)
        
        with app.app_context():
            db.session.add(organisation)
            db.session.commit()
    