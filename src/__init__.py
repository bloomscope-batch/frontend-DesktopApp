from flask import Flask
from flask_sqlalchemy import SQLAlchemy
import hashlib

def hash(string):
    string = string.encode("utf-8")
    sha256 = hashlib.sha256()
    sha256.update(string)
    string_hash = sha256.hexdigest()
    return string_hash

app = Flask(__name__)
app.config.from_pyfile("config.py")

db = SQLAlchemy(app)

from src import routes