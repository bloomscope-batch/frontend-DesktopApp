from src import db
from datetime import datetime


class User(db.Model):

    # Base user metadata

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.Text, unique = True, nullable = False)
    user_type = db.Column(db.Integer, nullable = False)
    email = db.Column(db.Text, nullable = False)
    phone = db.Column(db.Text, nullable = False)
    profile_pic = db.Column(db.String(64), nullable = False, default = "default_profile_pic")
    created_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)
    updated_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)

class Admin(db.Model):

    # Admin User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), db.ForeignKey('user.user_id'), unique = True, nullable = False)
    password =  db.Column(db.String(64), nullable = False)

class Student(db.Model):

    # Student User

    # add a column to store the tests (test_id(s)) assigned to the students

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.String(64), db.ForeignKey('user.user_id'), unique = True, nullable = False)
    name = db.Column(db.Text, nullable = False)
    dob = db.Column(db.Text, nullable = False)
    parent_id = db.Column(db.Text, db.ForeignKey('parent.user_id'), nullable = False)
    org_id = db.Column(db.Text, db.ForeignKey('organization.user_id'), nullable = True)
    password =  db.Column(db.String(64), nullable = False)

    organization = db.relationship('Organization', backref = 'students')
    parent = db.relationship('Parent', backref = 'students')

    # tests = db.relationship('Test', backref='student')

class Parent(db.Model):

    # Parent User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.Text, db.ForeignKey('user.user_id'), unique = True, nullable = False)
    password =  db.Column(db.String(64), nullable = False)

class Organization(db.Model):

    # Organization User

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.Text, db.ForeignKey('user.user_id'), unique = True, nullable = False)
    name = db.Column(db.Text, nullable = False)
    password =  db.Column(db.String(64), nullable = False)

class User_sessions(db.Model):

    id = db.Column(db.Integer, primary_key = True)
    user_id = db.Column(db.Text, db.ForeignKey('user.user_id'), unique = True, nullable = False)
    access_token = db.Column(db.String(64), nullable = False)
    created_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)
    due_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)

class Test(db.Model):

    # Basic test metadata

    id = db.Column(db.Integer, primary_key = True)
    test_id = db.Column(db.String(64), unique = True, nullable = False)
    name = db.Column(db.Text, nullable = False)
    created_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)
    updated_at = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)
    deadline = db.Column(db.DateTime, default = datetime.utcnow, nullable = False)
    assigned_to = db.Column(db.Text, nullable = False)

class test_questions(db.Model):

    # questions in the tests

    id = db.Column(db.Integer, primary_key = True)
    test_id = db.Column(db.String(64), nullable = False)
    question_id = db.Column(db.String(64), nullable = False)
    options = db.Column(db.Text, nullable = False)
    correct_option = db.Column(db.Text, nullable = False)
    marks = db.Column(db.Integer, nullable = False)
    marks_distribution = db.Column(db.Integer, nullable = True)

class test_results(db.Model):

    # results of tests

    id = db.Column(db.Integer, primary_key = True)
    test_id = db.Column(db.String(64), nullable = False)
    student_id = db.Column(db.String(64), nullable = False)
    marks_distribution = db.Column(db.Integer, nullable = True)
    total_score = db.Column(db.Integer, nullable = False)