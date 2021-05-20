# Adapted from Andreas Zitzelsberger "az@az82.de" https://github.com/az82/docker-derby and Vasilii Chernov https://github.com/tjlee/dg_docker_files/tree/master/apache_derby_server

FROM adoptopenjdk:11-jre-hotspot

ENV DERBY_VERSION=10.15.2.0
ENV DERBY_HOME=/derby
ENV DERBY_LIB=${DERBY_HOME}/lib
ENV CLASSPATH=${DERBY_LIB}/derby.jar:${DERBY_LIB}/derbynet.jar:${DERBY_LIB}/derbytools.jar:${DERBY_LIB}/derbyoptionaltools.jar:${DERBY_LIB}/derbyclient.jar

RUN \
    apt-get update && apt-get install -y wget netcat && \
    wget https://dist.apache.org/repos/dist/release/db/derby/db-derby-${DERBY_VERSION}/db-derby-${DERBY_VERSION}-bin.tar.gz && \
    tar xzf /db-derby-${DERBY_VERSION}-bin.tar.gz && \
    mv /db-derby-${DERBY_VERSION}-bin /derby && \
    rm -Rf /*.tar.gz ${DERBY_HOME}/demo ${DERBY_HOME}/javadoc ${DERBY_HOME}/docs ${DERBY_HOME}/test ${DERBY_HOME}/*.html ${DERBY_HOME}/KEYS \
    apt-get purge wget netcat && \
    rm -rf /var/lib/apt/lists/*

COPY init.sql /upload/

WORKDIR /dbs
EXPOSE 1527

RUN java -Djdbc.drivers=org.apache.derby.jdbc.EmbeddedDriver org.apache.derby.tools.ij /upload/init.sql

CMD ["java", "-Dderby.stream.error.field=java.lang.System.out", "org.apache.derby.drda.NetworkServerControl", "start", "-h", "0.0.0.0"]
