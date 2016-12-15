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

#define MAKE_AND_TEST_RES(res, cmd_in) do { if(!isOk(res = PQexec(conn, cmd_in))) { \
                    PQclear(res); \
                    return NULL; }} while(0)

#define TEST_NOT_EMPTY(res, cmd_in, errorMsg) do{ \
                        MAKE_AND_TEST_RES(res, cmd_in); \
                        if(!isEmpty(res)) { \
                            printf(errorMsg); \
                            PQclear(res); \
                            return NULL; } \
                        PQclear(res); } while(0)

#define TEST_ID_EXIST(res, cmd, id) do{ \
                        sprintf(cmd, "SELECT * FROM Users WHERE ID = %d", id); \
                        TEST_NOT_EMPTY(res, cmd, ILL_PARAMS);\
                        } while(0)

#define TEST_IDS_FOLLOW(res, cmd, id1, id2) do{ \
                        sprintf(cmd, "SELECT * FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2); \
                        TEST_NOT_EMPTY(res, cmd, NOT_APPLICABLE);\
                        } while(0)

// The functions you have to implement
void* addUser(char* Name, int Age) {
    PGresult *res;
    char cmd[500];
    char *newID_query;
    int newID = 1;

    // part 1 - find out if the Users table is empty
    sprintf(cmd, "SELECT * FROM Users");
    MAKE_AND_TEST_RES(res, cmd);

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

        MAKE_AND_TEST_RES(res, newID_query);
        newID = atoi(PQgetvalue(res, 0, 0));
    }

    PQclear(res); // clear result

    sprintf(cmd, "INSERT INTO Users VALUES(%s, '%s', %d)", newID_query, Name, Age);
        // note that we didn't use the previous queries result. We used the sub-query as is
    MAKE_AND_TEST_RES(res, cmd);

    printf(ADD_USER, Name, Age);
    printf(ADD_USER_SUCCESS, newID, Name, Age);

    PQclear(res); // clear result
    return NULL;
}
void* removeUser(int ID) {
    return NULL;
}
void* follow(int ID1, int ID2) {
    PGresult *res;
    char cmd[500];

    TEST_ID_EXIST(res, cmd, ID1);
    TEST_ID_EXIST(res, cmd, ID2);

    TEST_IDS_FOLLOW(res, cmd, ID1, ID2);

    sprintf(cmd, "INSERT INTO Follows VALUES(%d, %d)", ID1, ID2);
    MAKE_AND_TEST_RES(res, cmd);

    printf(SUCCESSFUL);
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
