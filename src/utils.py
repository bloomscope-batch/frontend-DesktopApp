import hashlib
import os
import smtplib
import re

def hash(string):
    string = string.encode("utf-8")
    sha256 = hashlib.sha256()
    sha256.update(string)
    string_hash = sha256.hexdigest()
    return string_hash

def create_session_token(created_at):
    return hash(created_at)

# def any_null(arg_list):
#     for arg in arg_list:
#         if arg == "":
#             return True
#     return False

def generate_username(user_type, email, phone):
    return hash(email)

def verify_email(email):
    email_regex_patter = r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,7}\b'
    if re.fullmatch(email_regex_patter, email):
        return True
    return False

def verify_phone(phone):
    if len(phone) == 10:
        return True
    return False

def verify_password_format(password, confirm_password):
    return True

def verify_password(password, confirm_password):
    format_verified_msg = verify_password_format(password)
    if format_verified_msg == True:
        if password == confirm_password:
            return True
        else:
            return "pwd and confirm pwd fields do not match"
    else:
        return format_verified_msg

def send_mail(reciever, subject, body):
    sender = "bloomscope.dev.mail@gmail.com"
    pwd = "bloomscope@iitb"
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as smtp:
        smtp.login(sender, pwd)
        msg = f"Subject: {subject}\n\n {body}"
        smtp.sendmail(sender, reciever, msg)