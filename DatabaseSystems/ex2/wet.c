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
// The functions you have to implement
void* addUser(char* Name, int Age) {
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
