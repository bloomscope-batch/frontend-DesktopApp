from src import db

class User(db.Model):

    # Global data for all

    id = db.Column(db.Integer, primary_key = True, auto_increment=True)
    user_id = db.Column(db.String(64), unique = True, nullable = False)
    email = db.Column(db.Text, unique = True, nullable = False)

    user_type = db.Column(db.Integer, nullable = False)

    password = db.Column(db.String(64), nullable = False)

    name = db.Column(db.Text, nullable = False)
    phone = db.Column(db.String(10), nullable = False)

    # Student user - null if not a student user

    dob = db.Column(db.String(10), nullable = True) # format : yyyy-mm-dd

    father_name = db.Column(db.Text, nullable = True)
    father_email = db.Column(db.Text, nullable = True)
    father_phone = db.Column(db.String(10), nullable = True)

    mother_name = db.Column(db.Text, nullable = True)
    mother_email = db.Column(db.Text, nullable = True)
    mother_phone = db.Column(db.String(10), nullable = True)
    
    special_needs = db.Column(db.Text, nullable = True)
    other_remarks = db.Column(db.Text, nullable = True)

    # Parent user specific - null if not a parent user

    self_relation = db.Column(db.Text, nullable = True)

    spouse_relation = db.Column(db.Text, nullable = True)
    spouse_name = db.Column(db.Text, nullable = True)
    spouse_email = db.Column(db.Text, nullable = True)
    spouse_phone = db.Column(db.String(10), nullable = True)

    # Organisation / teacher user - use the global user data

    # use the below for both organisation and parent
    # not yet in use, will add this attribute later
    # student_emails = db.Column(db.Text, nullable = True)