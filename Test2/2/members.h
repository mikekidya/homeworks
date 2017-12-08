#pragma once

struct Member;

Member *createMember(char *name, int danger);
void deleteMember(Member *member);

bool hasBiggerDanger(Member *member, Member *other);
bool hasBiggerName(Member *member, Member *other);

void sortByDanger(Member **array, int left, int right);
void sortByName(Member **array, int left, int right);

void printMember(Member *member);