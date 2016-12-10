#ifndef WET_H_
#define WET_H_

#include <stdbool.h>

/*==========================================================*/
/*  -----  DBMS course, 236363, Winter - 2016-2017    ----- */
/*==========================================================*/
/* This is a sample header file for the "wet" assignment.   */
/* This file should not be submitted, it'll be replaced     */
/* during the automatic tests. Therefore, DO NOT change     */
/* anything in this file except your USERNAME and PASSWORD. */

/* Update these two macros to your account's details */
/* USERNAME = t2 user, PASSWORD = your id number     */
#define USERNAME "cs236363"
#define PASSWORD "321773210"

/* DO NOT change the names of the macros -      */
/* your submitted program will fail compilation */
#define ILL_PARAMS           "Illegal parameters\n"
#define EMPTY                "Empty\n"
#define SUCCESSFUL           "Successful\n"
#define NOT_APPLICABLE	     "Not applicable\n"

/* Function names */
#define COMMAND_ADD_USER     "add_user"
#define COMMAND_REMOVE_USER  "remove_user"
#define COMMAND_FOLLOW       "follow"
#define COMMAND_UNFOLLOW     "unfollow"
#define COMMAND_FOLLOWING    "following"
#define COMMAND_POPULAR      "popular"
#define COMMAND_STAR         "star"
#define COMMAND_SUGGEST      "suggest"

#define ADD_USER             "ADD USER Name:%10s Age:%2d\n"
#define ADD_USER_SUCCESS     "ADD USER ID:%2d Name:%10s Age:%2d\n"
#define REMOVE_USER          "REMOVE USER ID:%2d\n"
#define FOLLOW               "FOLLOW FOLLOWER:%2d FOLLOWING:%2d\n"
#define UNFOLLOW             "UNFOLLOW FOLLOWER:%2d FOLLOWING:%2d\n"
#define FOLLOWING            "FOLLOWING\n"
#define POPULAR              "POPULAR K:%2d\n"
#define STAR                 "STAR K:%2d\n"
#define SUGGEST              "SUGGEST K:%2d\n"

#define FOLLOWING_RESULT     "ID:%2d NAME:%10s, HAS %2d FOLLOWERS\n"
#define POPULAR_RESULT       "ID:%2d NAME:%10s IS POPULAR\n"
#define STAR_RESULT          "Name:%10s IS STAR\n"
#define SUGGEST_RESULT        "YOU MAY KNOW: %10s\n"

// This function is implemented in the parser.c file.
void parseInput();

// The functions you have to implement:
// All of the functions gets strings because we don't want you to process this input
// in the C language, but forward it as is to the SQL.
void* addUser(char* Name, int Age);
void* removeUser(int ID);
void* follow(int ID1, int ID2);
void* unfollow(int ID1, int ID2);
void* following();
void* popular(int K);
void* star(int K);
void* suggest(int ID);

#endif /* WET_H_ */
