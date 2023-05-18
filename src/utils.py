from src import app, db
from src.models import pwd_reset_hash

import hashlib
import re
import datetime

import smtplib
import ssl
from email.message import EmailMessage

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

def generate_username(user_type, phone):
    return user_type[0:3] + phone

def generate_access_token(username, login_time):
    return hash(username + str(login_time))

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

def create_mail(sender, receiver, subject, body):

    em = EmailMessage()
    em['From'] = sender
    em['To'] = receiver
    em['Subject'] = subject
    em.set_content(body)

    return em

def send_mail(reciever, subject, body):
    sender = "bloomscope.dev.mail@gmail.com"
    pwd = "deuvtimhklddheon"
    context = ssl.create_default_context()
    with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as smtp:
        smtp.login(sender, pwd)
        msg = create_mail(sender, reciever, subject, body)
        smtp.sendmail(sender, reciever, msg.as_string())

def send_pwd_reset_mail(email, username, current_time):
    uniq_id = hash(email + username + str(current_time))
    reset_link = f"http://localhost:5000/reset-pwd/{uniq_id}/"
    subject = "password reset link"
    body = f"Click <a href='{reset_link}'>here</a> to reset password. This link wil be valid only for next 10 minutes."
    send_mail(email, subject, body)
    with app.app_context():
        link_exists = db.session.query(pwd_reset_hash.query.filter_by(user_id = username).exists()).scalar()
        time_change = datetime.timedelta(minutes = 10)
        if link_exists:
            link = pwd_reset_hash.query.filter_by(user_id = username).first()
            link.hash_id = uniq_id
            link.expire_at = current_time + time_change
        else:
            db_otp_store = pwd_reset_hash(user_id = username, hash_id = uniq_id, expire_at = current_time + time_change)
            db.session.add(db_otp_store)
        db.session.commit()