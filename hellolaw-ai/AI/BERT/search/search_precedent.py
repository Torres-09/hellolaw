import os
import pickle
import time

import numpy as np
import pandas as pd

from pydantic import BaseModel
from sklearn.metrics.pairwise import cosine_similarity
from AI.utils.load_vector_data import load_vector_data


class Precedent(BaseModel):
    index: int
    case_no: str
    judmn_adju_de: str
    court_nm: str
    case_nm: str
    case_field: int
    detail_field: int
    trail_field: int
    relate_laword: list
    disposal_content: str
    basic_fact: str
    court_dcss: str
    conclusion: str


def extract_laws(text, separator=',', limit=3):
    # 입력이 빈 문자열인 경우 빈 리스트 반환
    if not text: return []

    # 문자열을 지정된 구분자로 분리
    laws = text.split(separator)

    # 빈 문자열 요소 제거
    laws = [law.strip() for law in laws if law.strip()]

    # 처음 'limit'개의 법률을 반환하거나, 법률의 수가 'limit'보다 적다면 모두 반환
    return laws[:min(len(laws), limit)]

def search_precedent(input_sequence: str,model, text_data, compare_vector):
    start= time.time()

    input_vector = np.expand_dims(model.encode(input_sequence), axis=0)

    cos_sim = cosine_similarity(input_vector, compare_vector)
    data_cosine = np.sort(cos_sim).squeeze()[::-1][:3]
    top_question = np.argsort(cos_sim).squeeze()[::-1][:3]

    print(top_question)
    print(data_cosine)

    precendent = Precedent(
        index = top_question[0],
        case_no = text_data[top_question[0]][0],
        judmn_adju_de = text_data[top_question[0]][1],
        court_nm = text_data[top_question[0]][2],
        case_nm = text_data[top_question[0]][3],
        case_field = text_data[top_question[0]][4],
        detail_field = text_data[top_question[0]][5],
        trail_field = text_data[top_question[0]][6],
        relate_laword = extract_laws(text_data[top_question[0]][7]),
        disposal_content = text_data[top_question[0]][8],
        basic_fact = text_data[top_question[0]][9],
        court_dcss = text_data[top_question[0]][10],
        conclusion = text_data[top_question[0]][11]
    )

    print("유사판례 가져오기 time: ", time.time()-start, "s 소요")
    return precendent

if __name__ == "__main__":
     print(search_precedent("계약 기간이 만료되어 다른 집으로 이사를 가려고 했는데, 집주인이 전세금을 돌려주지 않아요. 3억을 못받았어요 어떻게 해야하나요?",
                            np.array(pd.read_csv("./output_drop.csv")),
                            load_vector_data("./law_data_drop_vector.bin")))
