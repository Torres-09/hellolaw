FROM python:latest

WORKDIR /app/

COPY ./*.py /app/
COPY ./.env /app/
COPY ./requirements.txt /app/

RUN pip install -r requirements.txt

CMD uvicorn --host=0.0.0.0 --port 8000 main:app
