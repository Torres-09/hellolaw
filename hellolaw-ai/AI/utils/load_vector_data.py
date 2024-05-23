import os
import pickle


def load_vector_data():
    # 파일 이름 정의
    filename = "precedent_vector.bin"
    # 파일의 절대 경로 구성
    file_path = os.path.join(os.path.dirname(__file__), filename)

    if os.path.isfile(file_path):
        with open(file_path, "rb") as fr:
            vector_data = pickle.load(fr)
    else:
        print("판례 데이터가 존재하지 않습니다.")
        vector_data = None
    return vector_data
