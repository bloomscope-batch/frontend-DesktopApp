from src import db
from datetime import datetime


class User(db.Model):

    # Base user metadata

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), unique = True, nullable = False)
    user_type = db.Column(db.Integer, nullable = False)
    email = db.Column(db.Text, unique = True, nullable = False)
    phone = db.Column(db.Text, unique = True, nullable = False)
    profile_pic = db.Column(db.String(64), unique = True, nullable = False, default = "profile_picture")
    created_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)
    updated_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)

class Admin(db.Model):

    # Admin User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), unique = True, nullable = False)
    password =  db.Column(db.String(64), nullable = False)

class Student(db.Model):

    # Student User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), unique = True, nullable = False)
    name = db.Column(db.Text, nullable = False)
    dob = db.Column(db.Text, nullable = False)
    parent_id = db.Column(db.String(64), db.ForeignKey('parent.user_id'), nullable=False)
    org_id = db.Column(db.String(64), db.ForeignKey('organization.user_id'), nullable=False)
    password =  db.Column(db.String(64), nullable = False)

class Parent(db.Model):

    # Parent User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), unique = True, nullable = False)
    password =  db.Column(db.String(64), nullable = False)

class Organization(db.Model):

    # Organization User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), unique = True, nullable = False)
    name = db.Column(db.Text, nullable = False)
    password =  db.Column(db.String(64), nullable = False)

class Student(db.Model):
    # ...

    # Set up a one-to-one relationship between students and user_profiles
    user_profile = db.relationship('User', backref='student', uselist=False)

class Parent(db.Model):
    # ...

    # Set up a one-to-one relationship between parents and user_profiles
    user_profile = db.relationship('User', backref='parent', uselist=False)

class Organization(db.Model):
    # ...

    # Set up a one-to-one relationship between organizations and user_profiles
    user_profile = db.relationship('User', backref='organization', uselist=False)

class Admin(db.Model):
    # ...

    # Set up a one-to-one relationship between admins and user_profiles
    user_profile = db.relationship('User', backref='admin', uselist=False)