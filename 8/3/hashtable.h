#pragma once

#include <fstream>
#include "string.h"


struct HastTable;

HastTable *createTable();
void deleteTable(HastTable *hastTable);

void add(HastTable *hastTable, String *string);
bool contains(HastTable *hastTable, String *string);

double loadFactor(HastTable *hastTable);
int numberOfElements(HastTable *hastTable);
int numberOfWords(HastTable *hastTable);
int numberOfEmptyBuckets(HastTable *hastTable);

int printMaxBucket(HastTable *hastTable, std::ostream &stream);
void printElements(HastTable *hastTable, std::ostream &stream);