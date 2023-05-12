from flask import request, render_template, session, redirect, url_for
from src import app
from src.db_operations import register_user, login_user
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
    if session['username'] != None:
        return f"Logged in as {session['username']}"
    if request.method == "POST":
        user_data = request.form
        return user_data
    return render_template("login.html")

@app.route("/auth/register/", methods = ["GET", "POST"])
def register_route():
    if session['username'] != None:
        return f"Logged in as {session['username']}"
    if request.method == "POST":
        user_data = request.form
        register_msg = register_user(user_data)
        if register_msg['message'] == "registered":
            current_time = str(datetime.utcnow())
            session['username'] = user_data.get("email")
            session['access_token'] = create_session_token(current_time)
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