from flask import request, render_template
from src import app
from src.models import User
from src.db_operations import register_user

@app.route("/auth/login", methods = ["GET", "POST"])
def login():
    if request.method == "POST":
        user_data = request.form
        return user_data
    return render_template("login.html")

@app.route("/auth/register", methods = ["GET", "POST"])
def register():
    if request.method == "POST":
        user_data = request.form
        register_msg = register_user(user_data)
        return user_data
    return render_template("register.html")