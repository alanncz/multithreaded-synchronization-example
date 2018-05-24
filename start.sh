#!/bin/bash
mvn clean package
sudo docker build -t image/multithreaded .
sudo docker run -i -t --name multithreaded image/multithreaded
