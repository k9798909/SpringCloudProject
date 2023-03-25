FROM golang:1.20.1-alpine3.17
WORKDIR /usr/src/app
COPY ./ ./
RUN go mod download
RUN go build -v -o /usr/local/bin/app ./
EXPOSE 8088
CMD ["app"]