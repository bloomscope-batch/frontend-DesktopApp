""" GENERATIONG THE SECRET KEY

import uuid
SECRET_KEY = uuid.uuid4().hex

"""

SECRET_KEY = '09def05c3c704081b8a4fc45ed8c7585'


""" DATABASE URI RULES ---------------------------------------

# Unix/Mac (note the four leading slashes)
# sqlite:////absolute/path/to/foo.db

# Windows (note 3 leading forward slashes and backslash escapes)
# sqlite:///C:\\absolute\\path\\to\\foo.db

# Windows (alternative using raw string)
# r'sqlite:///C:\absolute\path\to\foo.db'

# -------------------------------------------------------------

"""

import os

pwd = os.getcwd()
db_path = os.path.join("bloomscope.db")
# using sqlite for now 
SQLALCHEMY_DATABASE_URI = f"sqlite:///bloomscope.sqlite"
# SQLALCHEMY_DATABASE_URI = 'postgresql://roguecoder:pip3py#kali@@localhost:5432/bloomscope'
# SQLALCHEMY_TRACK_MODIFICATIONS = False

DEBUG = True

UPLOAD_FOLDER = "./test_ques"