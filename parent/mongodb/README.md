**BEFORE:**

Import data
```
mongoimport --db test --collection grades --drop --file grades.json
```
Creating admin user
```
use admin
db.createUser(
{
    user: "root",
    pwd: "password",
    roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase", "readWriteAnyDatabase"],
})
```
Creating test user
```
use test
db.createUser(
  {
    user: "test",
    pwd: "test123",
    roles: [ { role: "readWrite", db: "test" }]
  }
)
```

**TASKS:**
1. INSERT COLLECTION
https://www.mkyong.com/mongodb/java-mongodb-insert-a-document/
2. SHOW DOCUMENTS NUMBER INSIDE COLLECTION
3. STUDENTS ID > 100
4. STUDENT GRADES WHERE ID > 100 AND GRADE TYPE = EXAM
5. STUDENT IDS WITH AVG
6. SORT STUDENT IDS
7. INCREMENT ALL EXAM GRADES IF THEY ARE IN RANGE <10,20>
https://docs.mongodb.com/manual/reference/operator/update/max/#up._S_max
8. MAP DATA

**IMPORTANT:**
* https://docs.mongodb.com/manual/crud/
* https://docs.mongodb.com/manual/tutorial/insert-documents/
* https://docs.mongodb.com/manual/tutorial/query-documents/
* https://docs.mongodb.com/manual/tutorial/update-documents/
* https://docs.mongodb.com/manual/tutorial/remove-documents/
* https://docs.mongodb.com/manual/aggregation/