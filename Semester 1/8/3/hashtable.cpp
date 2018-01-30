#include "hashtable.h"

struct TableElement
{
    String *string;
    int count;
    TableElement *nextElement;
};

struct HastTable
{
    int const size = 10007;
    TableElement **bucket;
};

int hash(String *string, int mod)
{
    int const prime = 23;

    int result = 0;
    int power = 1;

    for (int i = 0; i < stringLength(string); i++)
    {
        result = (result + (power * (int) getChar(string, i)) % mod) % mod;
        power = (power * prime) % mod;
    }

    return result;
}

HastTable *createTable()
{
    HastTable *newTable = new HastTable;

    newTable->bucket = new TableElement*[newTable->size];
    for (int i = 0; i < newTable->size; i++)
        newTable->bucket[i] = nullptr;

    return newTable;
}

void deleteBucket(TableElement *tableElement)
{
    if (tableElement == nullptr)
        return;

    deleteBucket(tableElement->nextElement);
    deleteString(tableElement->string);
    delete tableElement;
}

void deleteTable(HastTable *hastTable)
{
    for (int i = 0; i < hastTable->size; i++)
    {
        deleteBucket(hastTable->bucket[i]);
    }
    delete[] hastTable->bucket;
    delete hastTable;
}

void add(HastTable *hastTable, String *string)
{
    int index = hash(string, hastTable->size);

    TableElement *tmp = hastTable->bucket[index];
    while (tmp != nullptr)
    {
        if (isEqual(tmp->string, string))
        {
            tmp->count++;
            deleteString(string);
            return;
        }
        tmp = tmp->nextElement;
    }

    TableElement *newElement = new TableElement;
    newElement->count = 1;
    newElement->string = string;
    newElement->nextElement = hastTable->bucket[index];
    hastTable->bucket[index] = newElement;
}

bool contains(HastTable *hastTable, String *string)
{
    int index = hash(string, hastTable->size);

    TableElement *tmp = hastTable->bucket[index];
    while (tmp != nullptr)
    {
        if (isEqual(tmp->string, string))
            return true;
        tmp = tmp->nextElement;
    }
    return false;
}

int numberOfElements(HastTable *hastTable)
{
    int result = 0;
    for (int i = 0; i < hastTable->size; i++)
    {
        TableElement *tmp = hastTable->bucket[i];
        while (tmp != nullptr)
        {
            result++;
            tmp = tmp->nextElement;
        }
    }
    return result;
}

double loadFactor(HastTable *hastTable)
{
    return (double) numberOfElements(hastTable) / hastTable->size;
}

int numberOfWords(HastTable *hastTable)
{
    int result = 0;
    for (int i = 0; i < hastTable->size; i++)
    {
        TableElement *tmp = hastTable->bucket[i];
        while (tmp != nullptr)
        {
            result += tmp->count;
            tmp = tmp->nextElement;
        }
    }
    return result;
}

int numberOfEmptyBuckets(HastTable *hastTable)
{
    int result = 0;
    for (int i = 0; i < hastTable->size; i++)
        if(hastTable->bucket[i] == nullptr)
            result++;

    return result;
}


void printBucket(HastTable *hastTable, int bucket, std::ostream &stream)
{
    TableElement *tmp = hastTable->bucket[bucket];
    while (tmp != nullptr)
    {
        printString(tmp->string, stream);
        stream << " " << tmp->count << std::endl;
        tmp = tmp->nextElement;
    }

}

void printElements(HastTable *hastTable, std::ostream &stream)
{
    for (int i = 0; i < hastTable->size; i++)
        printBucket(hastTable, i, stream);
}

int printMaxBucket(HastTable *hastTable, std::ostream &stream)
{
    int maxValue = 0;
    int maxBucket = 0;
    for (int i = 0; i < hastTable->size; i++)
    {
        int current = 0;
        TableElement *tmp = hastTable->bucket[i];
        while (tmp != nullptr)
        {
            current++;
            tmp = tmp->nextElement;
        }
        if (current > maxValue)
        {
            maxValue = current;
            maxBucket = i;
        }
    }
    printBucket(hastTable, maxBucket, stream);
}