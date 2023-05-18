from flask import request, render_template, session, redirect, url_for

from src import app, db
from src.models import User, pwd_reset_hash
from src.db_operations import register_user, login_user, verify_login_session, reset_pwd
from src.utils import send_pwd_reset_mail

import csv
from datetime import datetime

@app.route("/")
def root_route():
    session['username'] = None
    session['access_token'] = None
    return redirect(url_for("register_route"))

@app.route("/auth/login/", methods = ["GET", "POST"])
def login_route():

    try:
        login_time = datetime.utcnow()
        if session['username'] != None:
            login_msg = verify_login_session(dict(session), login_time)
            if login_msg["message"] == "login success":
                return f"Logged in as {session['username']}"
            elif login_msg["message"] == "session expired":
                return "session expired login again."
            else:
                return render_template("login.html")
    except:
        pass
            
    if request.method == "POST":
        user_data = request.form
        login_msg, access_token = login_user(user_data, login_time)
        if login_msg["message"] == "login success":
            session['username'] = user_data["username"]
            session['access_token'] = access_token
            return f"Logged in as {session['username']}"
        elif login_msg["message"] == "user does not exist":
            return redirect(url_for("register_route"))
        else:
            return login_msg
    
    return render_template("login.html")

@app.route("/auth/register/", methods = ["GET", "POST"])
def register_route():

    login_time = datetime.utcnow()
    try:
        if session['username'] != None:
            return redirect(url_for("login_route"))
    except:
        pass
    
    if request.method == "POST":
        user_data = request.form
        register_msg, session_data = register_user(user_data, login_time)
        if register_msg['message'] == "registered":
            return redirect(url_for("login_route"))
        
    return render_template("register.html")

@app.route("/forgot-pwd/", methods = ["GET", "POST"])
def forgot_pwd_route():

    if request.method == "POST":

        form_data = request.form

        username = form_data['username']
        with app.app_context():
            user_exists = db.session.query(User.query.filter_by(user_id = username).exists()).scalar()
            if user_exists:
                email = User.query.filter_by(user_id = username).first().email
                current_time = datetime.utcnow()
                send_pwd_reset_mail(email, username, current_time)
                return {"message" : "link sent"}
            else:
                return {"message" : "username does not exist"}

    return render_template("forgot_pwd.html")

@app.route("/reset-pwd/<unique_id>/", methods = ["GET", "POST"])
def reset_pwd_route(unique_id):
    
    if request.method == "POST":
        form_data = request.form
        pwd = form_data['password']
        conf_pwd = form_data['confirm-password']
        if pwd == conf_pwd:
            reset_pwd(unique_id, pwd)
            return {"message" : "password reset success. login to continue."}
        else:
            return {"message" : "pass hields do not match."}
        
    with app.app_context():
        id_valid = db.session.query(pwd_reset_hash.query.filter_by(hash_id = unique_id).exists()).scalar()
        if not id_valid:
            return {"message" : "indvalid link."}
    
    return render_template("reset_password.html")

@app.route("/logout/")
def logout_route():
    session['username'] = None
    session['access_token'] = None
    return redirect(url_for("login_route"))

@app.route("/upload_test/", methods = ["GET", "POST"])
def uplaod_test():
    if request.method == 'POST':  
        f = request.files['file']
        f.save(f.filename)
        with open(f.filename, "r") as test_data:
            reader = csv.reader(test_data)
            for line in reader:
                print(line)
    return render_template("test_upload.html")