BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "user" (
	"id"	INTEGER NOT NULL,
	"user_id"	VARCHAR(64) NOT NULL,
	"user_type"	INTEGER NOT NULL,
	"email"	TEXT NOT NULL,
	"phone"	TEXT NOT NULL,
	"profile_pic"	VARCHAR(64) NOT NULL,
	"created_at"	DATETIME NOT NULL,
	"updated_at"	DATETIME NOT NULL,
	UNIQUE("email"),
	PRIMARY KEY("id"),
	UNIQUE("phone"),
	UNIQUE("profile_pic"),
	UNIQUE("user_id")
);
CREATE TABLE IF NOT EXISTS "admin" (
	"id"	INTEGER NOT NULL,
	"user_id"	VARCHAR(64) NOT NULL,
	"password"	VARCHAR(64) NOT NULL,
	UNIQUE("user_id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "parent" (
	"id"	INTEGER NOT NULL,
	"user_id"	VARCHAR(64) NOT NULL,
	"password"	VARCHAR(64) NOT NULL,
	UNIQUE("user_id"),
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "organization" (
	"id"	INTEGER NOT NULL,
	"user_id"	VARCHAR(64) NOT NULL,
	"name"	TEXT NOT NULL,
	"password"	VARCHAR(64) NOT NULL,
	PRIMARY KEY("id"),
	UNIQUE("user_id")
);
CREATE TABLE IF NOT EXISTS "student" (
	"id"	INTEGER NOT NULL,
	"user_id"	VARCHAR(64) NOT NULL,
	"name"	TEXT NOT NULL,
	"dob"	TEXT NOT NULL,
	"parent_id"	VARCHAR(64) NOT NULL,
	"org_id"	VARCHAR(64) NOT NULL,
	"password"	VARCHAR(64) NOT NULL,
	PRIMARY KEY("id"),
	UNIQUE("user_id"),
	FOREIGN KEY("org_id") REFERENCES "organization"("user_id"),
	FOREIGN KEY("parent_id") REFERENCES "parent"("user_id")
);
COMMIT;
