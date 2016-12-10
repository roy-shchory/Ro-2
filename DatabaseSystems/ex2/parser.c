#include <stdio.h>
#include <string.h>
#include "wet.h"
#include <stdlib.h>
#include <stdbool.h>


#define MAX_LINE_SIZE 128

char	command_name[MAX_LINE_SIZE + 2];
char	command_line[MAX_LINE_SIZE + 2];
char*	arguments;
int     missing_argc;
int     argc;



void goto_next_line(FILE* file) {
    while (!feof(file) && fgetc(file) != '\n');
}

int check_command(char* expected_name) {
    return (!strcmp(command_name,expected_name));
}

int check_arguments(int expected_argc) {
    return ((missing_argc = expected_argc - argc) <= 0);
}

void illegalCommand() {
    printf("Illegal command!\n");
}

void parseInput() {
	bool exit = false;
    while ((!feof(stdin))&&(!exit)) {
        if (fgets(command_line,MAX_LINE_SIZE + 2,stdin) == NULL) break;
        if (strlen(command_line) > MAX_LINE_SIZE) {
            printf("Command too long, can not parse\n");
            goto_next_line(stdin);
        }
        else {
            missing_argc = 0;
            sprintf(command_name,"");
            sscanf(command_line,"%s",command_name);
			arguments = command_line + strlen(command_name) + 1;

            if (check_command(COMMAND_ADD_USER)) {
                char UserName[MAX_LINE_SIZE];
            	int Age;
                argc = sscanf(arguments, "%s %d", &UserName, &Age);
                check_arguments(2) ? addUser(UserName, Age) : illegalCommand();
            } else if (check_command(COMMAND_REMOVE_USER)) {
            	int ID;
            	argc = sscanf(arguments, "%d", &ID);
                check_arguments(1) ? removeUser(ID) : illegalCommand();
            } else if (check_command(COMMAND_FOLLOW)){
            	int ID1, ID2;
            	argc = sscanf(arguments, "%d %d", &ID1, &ID2);
                check_arguments(2) ? follow(ID1, ID2) : illegalCommand();
            } else if(check_command(COMMAND_UNFOLLOW)) {
                int ID1, ID2;
                argc = sscanf(arguments, "%d %d", &ID1, &ID2);
                check_arguments(2) ? unfollow(ID1, ID2) : illegalCommand();
            } else if (check_command(COMMAND_FOLLOWING)) {
                following();
            } else if (check_command(COMMAND_POPULAR)) {
                int k;
                argc = sscanf(arguments, "%d", &k);
                check_arguments(1) ? popular(k) : illegalCommand();
            } else if (check_command(COMMAND_STAR)) {
                int k;
                argc = sscanf(arguments, "%d", &k);
                check_arguments(1) ? star(k) : illegalCommand();
            } else if (check_command(COMMAND_POPULAR)) {
                int k;
                argc = sscanf(arguments, "%d", &k);
                check_arguments(1) ? popular(k) : illegalCommand();
            } else if (check_command(COMMAND_SUGGEST)) {
                int k;
                argc = sscanf(arguments, "%d", &k);
                check_arguments(1) ? suggest(k) : illegalCommand();
            } else if (missing_argc > 0) {
                printf("Missing %d argument(s) in command %s!\n",missing_argc,command_name);
            } else if (argc > 0) {
                illegalCommand();
            } else {
                illegalCommand();
            }
        }
    }
}
