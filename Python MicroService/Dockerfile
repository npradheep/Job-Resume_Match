FROM python
WORKDIR /usr/src/app
COPY . .
RUN pip install -r "requirements.txt"
EXPOSE 50051
ENTRYPOINT [ "python" ]

CMD [ "server.py" ]