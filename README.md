# QMM — Banking System Interface

QMM is a JavaFX desktop banking-system interface connected to a MySQL-compatible database. The application source and NetBeans project are located under:

`QMM+DB/QMM+DB/QMM/QMM/`

## Technology

- Java 17
- JavaFX
- Apache NetBeans (Ant-based project)
- MySQL / MariaDB
- MySQL Connector/J

## Database

A schema-only SQL setup file is provided at `qmmdb.sql`. It creates the `qmmdb` database and the tables used by the application:

- `users`
- `transactions`
- `feedback`

The SQL file intentionally contains no personal records, account balances, or sample passwords.

### Import the database

Using the MySQL command line:

```bash
mysql -u root -p < qmmdb.sql
```

You can also import `qmmdb.sql` through phpMyAdmin.

## Open in NetBeans

1. Install JDK 17 and a compatible JavaFX SDK.
2. Install MySQL or MariaDB and import `qmmdb.sql`.
3. Open the folder `QMM+DB/QMM+DB/QMM/QMM/` as an existing NetBeans project.
4. Add MySQL Connector/J to the project's `lib/` folder and ensure it is available on the project classpath.
5. Review the database connection settings in the Java source before running. The checked-in source currently uses a local MySQL connection at `localhost:3306/qmmdb` with the local `root` account; change it to match your own development environment. Do not commit real database passwords.
6. Build and run the project from NetBeans.

## Important security note

This repository is intended as a portfolio/demo project. Do not use it with real banking or personal data. The current application source contains direct SQL password comparisons and should be upgraded to use salted password hashing and parameterized database configuration before any real-world use.

## Repository layout

- `QMM+DB/QMM+DB/QMM/QMM/src/` — Java source, images, and styles
- `QMM+DB/QMM+DB/QMM/QMM/nbproject/` — NetBeans project metadata
- `qmmdb.sql` — schema-only database setup
