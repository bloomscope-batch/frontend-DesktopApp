from src import app, db

if __name__ == '__main__':

    # dev = Development serevr
    # prod = Production server

    server_env = "dev"
    if server_env == "prod":
        app.debug = False

    with app.app_context():
        db.create_all()
    app.run()