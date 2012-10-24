Ett Admin-konto skapas automatiskt med anv: admin pw: admin, du kan sedan ändra lösenord.

Du behöver configurera glassfish för att login-funktionen ska fungera:

Steg1: Gå in i admin-konsolen i glassfish, (högerklicka på glassfish under services)
Steg2: Navigera till Configurations-->Server-config --> security --> realms

Steg3: Fyll i följande information:

Realm Name: userrealm
Class Name: JDBCRealm

JAAS Context:                   jdbcRealm
JNDI:                           jdbc/filehostingdb
User Table:                     APP_USER
User Name Column:               USERNAME
Password Column:                PASSWORD
Group Table:                    SUBJECT_GROUP
Group Table User Name Column:   APPUSER_USERNAME
Group Name Column:              GROUPS
Assign Groups:                  default
Digest Algorithm:               none
Password Encryption Algorithm   none
