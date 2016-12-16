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

#define EXECUTE(res, cmd_in) do { if(!isOk(res = PQexec(conn, cmd_in))) { \
                    PQclear(res); \
                    return NULL; }} while(0)

#define EXECUTE_AND_CLEAR(res, cmd_in) do { EXECUTE(res, cmd_in); PQclear(res); } while(0)

#define _EXECUTE_AND_ASSERT_AND_CLEAR_AUX(res, cmd_in, errorMsg, toggle)    do{ \
                                                                                EXECUTE(res, cmd_in); \
                                                                                if(isEmpty(res) ^ toggle) { \
                                                                                    printf(errorMsg); \
                                                                                    PQclear(res); \
                                                                                    return NULL; } \
                                                                                PQclear(res); \
                                                                            } while(0)

#define EXECUTE_AND_ASSERT_NOT_EMPTY_AND_CLEAR(res, cmd_in, errorMsg) _EXECUTE_AND_ASSERT_AND_CLEAR_AUX(res, cmd_in, errorMsg, false)

#define EXECUTE_AND_ASSERT_EMPTY_AND_CLEAR(res, cmd_in, errorMsg) _EXECUTE_AND_ASSERT_AND_CLEAR_AUX(res, cmd_in, errorMsg, true)

#define ASSERT_ID_EXIST(id) do{ \
                                PGresult *_abcd_res; \
                                char _abcd_cmd[250]; \
                                sprintf(_abcd_cmd, "SELECT * FROM Users WHERE ID = %d", id); \
                                EXECUTE_AND_ASSERT_NOT_EMPTY_AND_CLEAR(res, _abcd_cmd, ILL_PARAMS);\
                            } while(0)

// The functions you have to implement
void* addUser(char* Name, int Age) {
    PGresult *res;
    char cmd[500];
    char *newID_query;
    int newID = 1;

    // part 1 - find out if the Users table is empty
    sprintf(cmd, "SELECT * FROM Users");
    EXECUTE(res, cmd);

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

        EXECUTE(res, newID_query);
        newID = atoi(PQgetvalue(res, 0, 0));
    }

    PQclear(res); // clear result

    sprintf(cmd, "INSERT INTO Users VALUES(%s, '%s', %d)", newID_query, Name, Age);
        // note that we didn't use the previous queries result. We used the sub-query as is
    EXECUTE(res, cmd);

    printf(ADD_USER, Name, Age);
    printf(ADD_USER_SUCCESS, newID, Name, Age);

    PQclear(res); // clear result
    return NULL;
}
void* removeUser(int ID) {
    PGresult *res;
    char cmd[500];

    printf(REMOVE_USER, ID);

    ASSERT_ID_EXIST(ID);

    sprintf(cmd, "DELETE FROM Users WHERE ID = %d", ID);
    EXECUTE_AND_CLEAR(res, cmd);

    sprintf(cmd, "DELETE FROM Follows WHERE ID1 = %d OR ID2 = %d", ID, ID);
    EXECUTE_AND_CLEAR(res, cmd);

    return NULL;
}
void* follow(int ID1, int ID2) {
    PGresult *res;
    char cmd[500];

    printf(FOLLOW, ID1, ID2);

    ASSERT_ID_EXIST(ID1);
    ASSERT_ID_EXIST(ID2);

    sprintf(cmd, "SELECT * FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2);
    EXECUTE_AND_ASSERT_EMPTY_AND_CLEAR(res, cmd, NOT_APPLICABLE);

    sprintf(cmd, "INSERT INTO Follows VALUES(%d, %d)", ID1, ID2);
    EXECUTE_AND_CLEAR(res, cmd);

    printf(SUCCESSFUL);
    return NULL;
}
void* unfollow(int ID1, int ID2) {
    PGresult *res;
    char cmd[500];

    printf(UNFOLLOW, ID1, ID2);

    ASSERT_ID_EXIST(ID1);
    ASSERT_ID_EXIST(ID2);

    sprintf(cmd, "SELECT * FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2);
    EXECUTE_AND_ASSERT_NOT_EMPTY_AND_CLEAR(res, cmd, NOT_APPLICABLE);

    sprintf(cmd, "DELETE FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2);
    EXECUTE_AND_CLEAR(res, cmd);

    printf(SUCCESSFUL);
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
