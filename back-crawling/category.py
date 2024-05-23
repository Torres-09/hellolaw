import openai
import os
from dotenv import load_dotenv

BASE_DIR = os.path.dirname(os.path.abspath(__file__))
load_dotenv(os.path.join(BASE_DIR, '.env'))
secretKey = os.environ["OPENAI_SECRET_KEY"]

lawKey = {"stalking": "STAKING",
          "sex crimes": "SEX_CRIME",
          "traffic accidents/drinking driving": "TRAFFIC_ACCIDENT_AND_DRINKING_DRIVING",
          "assault/injury": "ASSAULT_AND_INJURY",
          "drugs": "DRUGS",
          "fraud": "FRAUD",
          "divorce": "DIVORCE",
          "inheritance/households": "INHERITANCE_AND_HOUSEHOLD",
          "loans/unpaid/bond collection": "LOAN_AND_UNPAID_AND_BOND_COLLECTION",
          "administrative litigation": "ADMINISTRATIVE_LITIGATION",
          "consumer disputes": "CONSUMER_DISPUTES",
          "other": "OTHER"}

suffix = """
---------
The above is Korean law.
Please pick the most correct one out of the 12 categories I provided below that correspond to the title and content

categories
stalking, sex crimes, traffic accidents/drinking driving, assault/injury, drugs, fraud, divorce, inheritance/households, loans/unpaid/bond collection, administrative litigation, consumer disputes, other

Don't say reason. just say one word.
But, If it's ambiguous, please let me know "other"
"""

def createPrompt(name, content):
    name = "title\n" + name + "\n"
    content = "content\n" + content + "\n"
    return name + content + suffix


def getCategory(name, content):
    openai.api_key = secretKey
    response = openai.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "user", "content": createPrompt(name, content)}
        ],
        max_tokens=4000,
        stop=None,
        temperature=1.0
    )
    answer = response.choices[0].message.content
    return lawKey.get(answer, "OTHER")
