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

#define TEST_RES(res) if(!isOk(res)) { \
                    PQclear(res); \
                    return NULL; }

bool isEmpty(PGresult *res) {
    return PQntuples(res) == 0;
}

bool isIdExist(int ID) {

}

bool isFollowing(int ID1, int ID2) {

}

// The functions you have to implement
void* addUser(char* Name, int Age) {
    PGresult *res;
    char cmd[500];
    char *newID_query;
    int newID = 1;

    // part 1 - find out if the Users table is empty
    sprintf(cmd, "SELECT * FROM Users");
    TEST_RES(res = PQexec(conn, cmd));

    if(isEmpty(res)) {
        // if Users is empty, the new ID will be 1
        newID_query = "1";
    } else {
        // else Users isn't empty
        // we will create a sub-query and insert it to the INSERT INTO query

        // The idea is to take all of the IDs + 1, and keep only those that are not original IDs.
        //      for example:
        //      IDs = (1, 2, 4, 5); IDs+1 = (2, 3, 5, 6)
        //      IDs+1 EXCEPT IDs = (3, 6)
        //      from here we take the min -> 3
        newID_query = "(SELECT MIN(ID) AS ID FROM ("
                "SELECT ID + 1 AS ID FROM Users "
                "EXCEPT "
                "SELECT ID FROM Users) O"
                ")"; // will find the next good ID

        // find the new id (only for the printing part later)
        sprintf(cmd, newID_query);
        PQclear(res); // clear result

        TEST_RES(res = PQexec(conn, newID_query));
        newID = atoi(PQgetvalue(res, 0, 0));
    }

    PQclear(res); // clear result

    sprintf(cmd, "INSERT INTO Users VALUES(%s, '%s', %d)", newID_query, Name, Age);
        // note that we didn't use the previous queries result. We used the sub-query as is
    TEST_RES(res = PQexec(conn, cmd));

    printf(ADD_USER, Name, Age);
    printf(ADD_USER_SUCCESS, newID, Name, Age);

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
