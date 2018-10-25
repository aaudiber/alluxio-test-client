# Build

```
mvn package
```

# Run

```
# Generate an 80M file
dd if=/dev/zero of=80M count=$[80*1024] bs=1024
# Copy data to /test directory within Alluxio
for i in `seq 1 10`; do ${ALLUXIO_HOME}/bin/alluxio fs copyFromLocal 80M /test/$i; done

# Read data
java -cp 'target/alluxio-test-client-1.0-SNAPSHOT.jar:/path/to/alluxio-client.jar:/path/to/alluxio/conf/' alluxio.TestClient /test
```
