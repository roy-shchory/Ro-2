#include <stdio.h>
#include <stdlib.h>
#include "wet.h"
#include "libpq-fe.h"

// This is the global connection object
PGconn *conn;
//PGresult *res;

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

#define EXECUTE_CMD(cmd) do { PGresult *_abcd_res; EXECUTE(_abcd_res, cmd); PQclear(_abcd_res); } while(0)

#define _ASSERT_CMD_AUX(cmd, errorMsg, toggle)  do{ \
                                                    PGresult *_abcd_res; \
                                                    EXECUTE(_abcd_res, cmd); \
                                                    if(isEmpty(_abcd_res) ^ toggle) { \
                                                        printf(errorMsg); \
                                                        PQclear(_abcd_res); \
                                                        return NULL; } \
                                                    PQclear(_abcd_res); \
                                                } while(0)

#define ASSERT_NOT_EMPTY(cmd, errorMsg) _ASSERT_CMD_AUX(cmd, errorMsg, false)

#define ASSERT_EMPTY(cmd, errorMsg) _ASSERT_CMD_AUX(cmd, errorMsg, true)

#define ASSERT_ID_EXIST(id) do{ \
                                char _abcd_cmd[CMD_SIZE]; \
                                sprintf(_abcd_cmd, "SELECT * FROM Users WHERE ID = %d", id); \
                                ASSERT_NOT_EMPTY(_abcd_cmd, ILL_PARAMS);\
                            } while(0)

#define EXECUTE_AND_ASSERT_NOT_EMPTY(res, cmd) do{ \
                                                    EXECUTE(res, cmd); \
                                                    if(isEmpty(res)) { \
                                                        printf(EMPTY); \
                                                        PQclear(res); \
                                                        return NULL; } \
                                                } while(0)

#define FOLLOWING_QUERY_STR     "SELECT ID, Name, COUNT(ID1) AS c \
                                FROM (Users LEFT JOIN Follows ON Users.ID = Follows.ID2) O \
                                GROUP BY ID, Name \
                                ORDER BY c ASC, ID DESC"

// The functions you have to implement
void* addUser(char* Name, int Age) {
    PGresult *res;
    char cmd[CMD_SIZE];
    char *newID_query;
    int newID = 1;

    printf(ADD_USER, Name, Age);

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

    printf(ADD_USER_SUCCESS, newID, Name, Age);

    PQclear(res); // clear result
    return NULL;
}
void* removeUser(int ID) {
    char cmd[CMD_SIZE];

    printf(REMOVE_USER, ID);

    ASSERT_ID_EXIST(ID);

    sprintf(cmd, "DELETE FROM Users WHERE ID = %d", ID);
    EXECUTE_CMD(cmd);

    sprintf(cmd, "DELETE FROM Follows WHERE ID1 = %d OR ID2 = %d", ID, ID);
    EXECUTE_CMD(cmd);

    printf(SUCCESSFUL);
    return NULL;
}
void* follow(int ID1, int ID2) {
    char cmd[CMD_SIZE];

    printf(FOLLOW, ID1, ID2);

    // test for type 1 errors:
    if (ID1 == ID2) {
        printf(ILL_PARAMS);
        return NULL;
    }

    ASSERT_ID_EXIST(ID1);
    ASSERT_ID_EXIST(ID2);

    // test for type 2 error:
    sprintf(cmd, "SELECT * FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2);
    ASSERT_EMPTY(cmd, NOT_APPLICABLE);

    // insert cmd:
    sprintf(cmd, "INSERT INTO Follows VALUES(%d, %d)", ID1, ID2);
    EXECUTE_CMD(cmd);

    printf(SUCCESSFUL);
    return NULL;
}
void* unfollow(int ID1, int ID2) {
    char cmd[CMD_SIZE];

    printf(UNFOLLOW, ID1, ID2);

    ASSERT_ID_EXIST(ID1);
    ASSERT_ID_EXIST(ID2);

    sprintf(cmd, "SELECT * FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2);
    ASSERT_NOT_EMPTY(cmd, NOT_APPLICABLE);

    sprintf(cmd, "DELETE FROM Follows WHERE ID1 = %d AND ID2 = %d", ID1, ID2);
    EXECUTE_CMD(cmd);

    printf(SUCCESSFUL);
    return NULL;
}
void* following() {
    PGresult *res;
    char cmd[CMD_SIZE];
    int i = 0;

    printf(FOLLOWING);

    sprintf(cmd, FOLLOWING_QUERY_STR);
    EXECUTE_AND_ASSERT_NOT_EMPTY(res, cmd);

    for (; i < PQntuples(res); ++i) {
        printf(FOLLOWING_RESULT, atoi(PQgetvalue(res, i, 0)), PQgetvalue(res, i, 1), atoi(PQgetvalue(res, i, 2)));
    }

    PQclear(res);
    return NULL;
}
void* popular(int K) {
    PGresult *res;
    char cmd[CMD_SIZE];
    int i = 0;

    printf(POPULAR, K);

    sprintf(cmd, "SELECT ID, Name FROM (%s) F WHERE c >= %d ORDER BY ID DESC", FOLLOWING_QUERY_STR, K);
    EXECUTE_AND_ASSERT_NOT_EMPTY(res, cmd);

    for (; i < PQntuples(res); ++i) {
        printf(POPULAR_RESULT, atoi(PQgetvalue(res, i, 0)), PQgetvalue(res, i, 1));
    }

    PQclear(res);
    return NULL;
}
void* star(int K) {
    PGresult *res;
    char cmd[CMD_SIZE];
    int i = 0;

    printf(STAR, K);

    // crate temp table:
    // (the Following table with ages)
    char* following_aux = "CREATE TABLE temp_following AS "
            "SELECT "
            "u1.ID as id1, u1.age AS age1, u2.id AS id2, u2.age AS age2 "
            "FROM "
            "Follows LEFT JOIN Users AS u1 ON Follows.ID1 = u1.ID "
            "LEFT JOIN Users AS u2 ON Follows.ID2 = u2.ID";
    EXECUTE_CMD(following_aux);

    // crate temp table:
    // this table has 3 columns: (ID; average age of ID's followers; average age of users that ID follows, OR ID's age if he doesn't follow anyone)
    // we keep only the rows where the second column's value is bigger than the third column's value
    char *star_aux = "CREATE TABLE temp_star_aux AS "
            "SELECT "
            "Users.ID as id, "
            "Name, "
            "COALESCE(AVG(f2.age1), 0) AS avg_followers, "
            "COALESCE(AVG(f1.age2), age) AS avg_following_or_age "
            "FROM "
            "Users LEFT JOIN temp_following AS f1 ON Users.ID = f1.id1 "
            "LEFT JOIN temp_following AS f2 ON Users.ID = f2.id2 "
            "GROUP BY id, Name, Age";

    EXECUTE_CMD(star_aux);
    // drop temp table:
    EXECUTE_CMD("DROP TABLE temp_following");

    // find the users from star_aux that are popular:
    sprintf(cmd, "SELECT S.Name "
            "FROM "
            "(SELECT id, Name FROM temp_star_aux WHERE avg_followers > avg_following_or_age) S "
            "LEFT JOIN (%s) F ON S.id = F.ID "
            "WHERE F.c >= %d "
            "ORDER BY S.Name", FOLLOWING_QUERY_STR, K);
    EXECUTE(res, cmd);

    // drop temp table:
    EXECUTE_CMD("DROP TABLE temp_star_aux");

    if(isEmpty(res)) {
        printf(EMPTY);
        PQclear(res);
        return NULL;
    }

    for (; i < PQntuples(res); ++i) {
        printf(STAR_RESULT, PQgetvalue(res, i, 0));
    }

    PQclear(res);
    return NULL;
}
void* suggest(int ID) {
    PGresult *res;
    char cmd[CMD_SIZE];
    int i;

    printf(SUGGEST, ID);

    ASSERT_ID_EXIST(ID);

    // get num of rows in Follows:
    EXECUTE(res, "SELECT * FROM Follows");
    int numOfRowsInFollows = PQntuples(res);
    PQclear(res);

    // create a temp table (temp_extended_follows):
    // this table will have 2 columns (id1, id2).
    // (id1, id2) is in the table if there is a followers path from id1 to id2 of any length
    EXECUTE_CMD("CREATE TABLE temp_extended_follows AS SELECT * FROM Follows");

    // add to temp_extended_follows:
    for (i = 0; i < numOfRowsInFollows; ++i) {
        EXECUTE_CMD("INSERT INTO temp_extended_follows (ID1, ID2) SELECT tef.ID1, Follows.ID2 "
                            "FROM "
                            "temp_extended_follows AS tef LEFT JOIN Follows ON tef.ID2 = Follows.ID1 "
                            "WHERE Follows.ID2 IS NOT NULL");
    }

    // remove paths of len 0 or 1. place result in temp_extended_follows2:
    EXECUTE_CMD("DELETE FROM temp_extended_follows WHERE ID1 = ID2");
    EXECUTE_CMD("CREATE TABLE temp_extended_follows2 AS "
                        "SELECT * FROM temp_extended_follows "
                        "EXCEPT "
                        "SELECT * FROM Follows");
    EXECUTE_CMD("DROP TABLE temp_extended_follows");

    //OR (ID1 IN (SELECT ID1 FROM Follows) AND ID2 IN (SELECT ID2 FROM Follows))

    // find the suggest list for ID:
    sprintf(cmd, "SELECT DISTINCT Users.Name FROM "
            "temp_extended_follows2 LEFT JOIN Users ON temp_extended_follows2.ID2 = Users.ID "
            "WHERE ID1 = %d "
            "ORDER BY Name ASC", ID);
    EXECUTE(res, cmd);
    EXECUTE_CMD("DROP TABLE temp_extended_follows2");

    // print stuff:
    if(isEmpty(res)) {
        printf(EMPTY);
        PQclear(res);
        return NULL;
    }

    for (i = 0; i < PQntuples(res); ++i) {
        printf(SUGGEST_RESULT, PQgetvalue(res, i, 0));
    }

    PQclear(res);
    return NULL;
}
