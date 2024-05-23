import pickle

import numpy as np
import pandas as pd
from sentence_transformers import SentenceTransformer
from tqdm import tqdm

print("실행되고 있는 중")

model_name = "jhgan/ko-sroberta-multitask"
model = SentenceTransformer(model_name)

print("실행되고 있는 중2")

df = pd.read_csv("./output.csv", encoding="UTF-8")
df = df.dropna(
    subset=["basicFact"]
)
df.to_csv("./output_drop.csv", index=False)
np_df = np.array(df)

vector_list = []
for i in tqdm(range(len(np_df))):
    Ab:str = np_df[i][11]
    Ab_query = model.encode(Ab)
    vector_list.append(list(Ab_query))

with open("./law_data_drop_vector.bin", "wb") as fw:
    pickle.dump(vector_list, fw)