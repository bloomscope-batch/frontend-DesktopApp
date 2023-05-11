from src.utils import verify_email, verify_phone

def verify_basic_form(email, phone):

    email_verified = verify_email(email)
    phone_verified = verify_phone(phone)
    
    if email_verified == phone_verified == True:
        return True
    return False

def verify_student_form(dob, parent_details):
    return True

def verify_parent_form():
    return True