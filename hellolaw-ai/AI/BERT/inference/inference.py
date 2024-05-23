import numpy as np
import pandas as pd
from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

from AI.utils.load_vector_data import load_vector_data


def extract_laws(text, separator='&', limit=3):
    # 문자열을 지정된 구분자로 분리
    laws = text.split(separator)

    # 처음 'limit'개의 법률을 반환하거나, 법률의 수가 'limit'보다 적다면 모두 반환
    return laws[:min(len(laws), limit)]


def BERT_infer(input):
    print("실행되고 있는 중")

    model_name = "jhgan/ko-sroberta-multitask"
    model = SentenceTransformer(model_name)

    print("실행되고 있는 중2")

    input_vector = model.encode(input)
    input_vecotr = np.expand_dims(input_vector, axis=0)

    print("실행되고 있는 중3")

    text_data = np.array(pd.read_csv("./output_drop.csv"))
    vector_data = load_vector_data("./law_data_drop_vector.bin")

    print("실행되고 있는 중4")

    cos_sim = cosine_similarity(input_vecotr, vector_data)
    data_cosine = np.sort(cos_sim).squeeze()[::-1][:5]
    top_question = np.argsort(cos_sim).squeeze()[::-1][:5]

    print("실행되고 있는 중5")

    pan_list = []

    pan_list.append(f"기초사실: {text_data[top_question[0]][11]}")
    pan_list.append(f"재판부의 판단: {text_data[top_question[0]][12]}")
    pan_list.append(f"처분내용: {text_data[top_question[0]][10]}")
    pan_list.append(f"관련법령/참조조문: {extract_laws(text_data[top_question[0]][7])}")

    return pan_list if data_cosine[0] >= 0.48 else "유사한 판례가 없습니다."


if __name__ == "__main__":
    print(*BERT_infer("계약 기간이 만료되어 다른 집으로 이사를 가려고 했는데, 집주인이 전세금을 돌려주지 않아요. 3억을 못받았어요 어떻게 해야하나요?"), sep="\n")
