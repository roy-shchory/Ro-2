#include <stdio.h>
#include <stdlib.h>
#include "wet.h"
#include "libpq-fe.h"

// This is the global connection object
PGconn *conn;
PGresult *res;

#define CMD_SIZE 550

int main(int argc, char** argv) {
    /* Make a connection to the DB. If parameters omitted, default values are used */
    char connect_param[CMD_SIZE + 2];
    sprintf(connect_param,"host=pgsql.cs.technion.ac.il dbname=%s user=%s password=%s",
                                                        USERNAME, USERNAME, PASSWORD);
    conn = PQconnectdb(connect_param);
    parseInput();
    PQfinish(conn);
    return 0;
}

/*************************************************************************************/
bool isOk(PGresult *res) {
    if (!res || (PQresultStatus(res) != PGRES_TUPLES_OK && PQresultStatus(res) != PGRES_COMMAND_OK)) {
        fprintf(stderr, "Error executing query: %s\n", PQresultErrorMessage(res));
        return false;
    }
    return true;
}

bool isEmpty(PGresult *res) {
    return PQntuples(res) == 0;
}

// The functions you have to implement
void* addUser(char* Name, int Age) {
    PGresult *res;
    char cmd[500];
    char *newID_query;
    int newID = 1;

    sprintf(cmd, "SELECT * FROM Users");
    res = PQexec(conn, cmd);

    if(!isOk(res)) {
        PQclear(res);
        return NULL;
    } else if(isEmpty(res)) {
        newID_query = "1";
    } else {
        newID_query = "(SELECT MIN(ID) AS ID FROM ("
                "SELECT ID + 1 AS ID FROM Users "
                "EXCEPT "
                "SELECT ID FROM Users) O"
                ")"; // will find the next good ID

        // find the new id (only for the printing part)
        sprintf(cmd, newID_query);
        PQclear(res); // clear result

        res = PQexec(conn, newID_query);
        newID = atoi(PQgetvalue(res, 0, 0));
    }

    PQclear(res); // clear result

    sprintf(cmd, "INSERT INTO Users VALUES(%s, '%s', %d)", newID_query, Name, Age); // note that we didn't use the previous queries result
    res = PQexec(conn, cmd);

    if(isOk(res)) {
        printf(ADD_USER, Name, Age);
        printf(ADD_USER_SUCCESS, newID, Name, Age);
    }

    PQclear(res); // clear result
    return NULL;
}
void* removeUser(int ID) {
    return NULL;
}
void* follow(int ID1, int ID2) {
    return NULL;
}
void* unfollow(int ID1, int ID2) {
    return NULL;
}
void* following() {
    return NULL;
}
void* popular(int K) {
    return NULL;
}
void* star(int K) {
    return NULL;
}
void* suggest(int ID) {
    return NULL;
}
