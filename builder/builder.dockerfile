FROM openjdk:21-jdk-slim
LABEL maintainer="octavio.roncal <octavioroncal@siani.es>"
LABEL version="8.0.0"
LABEL description="Magritte Compiler"
LABEL operations="Build"
LABEL targets="Java"
COPY out/build/builder/builder.jar /root/app/
COPY out/build/builder/lib /root/app/lib
COPY builder/docker/run-builder.sh /root/app/
WORKDIR /root/app
RUN chmod +x /root/app/run-builder.sh
ENTRYPOINT ["/root/app/run-builder.sh"]