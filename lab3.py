#!/usr/bin/python3
import pymysql
from tabulate import tabulate
import datetime


class Parent:
    def __init__(self):
        self._connection = pymysql.connect(
            "localhost", "root", "Yash@12345", "sakila", 3306)

    def _db_create(self):
        conn = self._connection
        cursor = conn.cursor()

    def create_actor(self, first_name, last_name):
        cursor = self._connection.cursor()
        conn = self._connection
        insert_query = "INSERT INTO actor (first_name, last_name) VALUES (%s, %s)"
        actor = (first_name, last_name)
        cursor.execute(insert_query, actor)
        conn.commit()
        print(cursor.rowcount, "record is inserted")
        dbconn = self._connection.close()


class Child(Parent):  # define child class
    def __init__(self):
        Parent.__init__(self)
        print("Calling child constructor")

    def show_newactor(self, last_name):
        cursor = self._connection.cursor()
        conn = self._connection
        actor = (last_name, )
        show_actor = "SELECT * FROM actor WHERE last_name = %s"
        cursor.execute(show_actor, actor)
        added_actor = cursor.fetchall()
        # Now print fetched result in a tabular format using tabulate
        headers = ['actor_id', 'first_name', 'last_name', 'last_update']
        print(tabulate(added_actor, headers, tablefmt="psql"))


c = Child()

# Creates the actor
# c.create_actor('jacob', 'green')

# Uncomment to Retrive the actor
c.show_newactor('green')
