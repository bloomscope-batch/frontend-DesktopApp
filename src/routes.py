from flask import request, render_template, session, redirect, url_for
from src import app
from src.db_operations import register_user, login_user, verify_login_session
from src.utils import create_session_token
import csv
from datetime import datetime

@app.route("/")
def root_route():
    session['username'] = None
    session['access_token'] = None
    return redirect(url_for("register_route"))

@app.route("/sample", methods = ["GET", "POST"])
def sample():
    if request.method == "POST":
        return request.form
    return render_template("sample.html")

@app.route("/auth/login/", methods = ["GET", "POST"])
def login_route():

    login_time = datetime.utcnow()
    if session['username'] != None:
        login_msg = verify_login_session(dict(session), login_time)
        if login_msg["message"] == "login success":
            return f"Logged in as {session['username']}"
        elif login_msg["message"] == "session expired":
            return "session expired login again."
        else:
            return render_template("login.html")
        
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
    if session['username'] != None:
        login_msg = verify_login_session(dict(session), login_time)
        if login_msg["message"] == "login success":
            return f"Logged in as {session['username']}"
        elif login_msg["message"] == "session expired":
            return "session expired login again."
        else:
            return redirect(url_for("login_route"))
    
    if request.method == "POST":
        user_data = request.form
        register_msg, session_data = register_user(user_data, login_time)
        if register_msg['message'] == "registered":
            session['username'] = session_data['username']
            session['access_token'] = session_data['access_token']
            return redirect(url_for("login_route"))
        
    return render_template("register.html")

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