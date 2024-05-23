from fastapi import FastAPI
from sentence_transformers import SentenceTransformer
from AI.BERT.search.search_precedent import *

from pydantic import BaseModel

from AI.GPTAssistant import AssistantService
from AI.utils.load_vector_data import load_vector_data
from dotenv import load_dotenv
load_dotenv(".env")

import time
import os
import pandas as pd
from openai import OpenAI


app = FastAPI()


# llm = None
# tokenizer = None
bertModel = None
text_data = None
compare_vector = None
client = None

class Question(BaseModel):
    text : str


@app.on_event("startup")
def serverInit():
    global bertModel, text_data, compare_vector, client

    start_time = time.time()
    print("Loading BERT Model...")
    os.environ["SENTENCE_TRANSFORMERS_HOME"] = './model/BERT'

    model_name = "jhgan/ko-sroberta-multitask"
    bertModel = SentenceTransformer(model_name)
    text_data = np.array(pd.read_csv("precedent_text.csv"))
    compare_vector = load_vector_data()

    print("BERT Model Loaded!")
    print(f"bert loading time: {time.time() - start_time}")

    client = OpenAI(
        api_key=os.environ["OPENAI_API_KEY"]
    )



@app.get("/health")
async def root():
    return {"message": "health"}



@app.post("/answer/")
async def answer(question: Question):
    print(question)
    return AssistantService.getAnswer(client, question.text)

@app.post("/search/")
async def searchPrecedent(question: Question):
    print(question)
    return search_precedent(question.text, bertModel, text_data, compare_vector)


