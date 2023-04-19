import hashlib

def hash(string):
    string = string.encode("utf-8")
    sha256 = hashlib.sha256()
    sha256.update(string)
    string_hash = sha256.hexdigest()
    return string_hash

def any_null(arg_list):
    for arg in arg_list:
        if arg == "":
            return True
    return False

def verify_email(email):
    return True

def verify_password_format(password):
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
