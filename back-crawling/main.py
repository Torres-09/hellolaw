from starlette.responses import JSONResponse

import crawl
import category
from fastapi import FastAPI

app = FastAPI(root_path="/crawling/")

class CustomException(Exception):
    def __init__(self, status_code: int, message: str):
        self.status_code = status_code
        self.message = message

@app.get("law")
async def getLawInfo(name: str):
    try:
        lawName, contents = crawl.getLawContents(name.strip())
        return {"lawName": lawName,
                "contents": contents,
                "category": category.getCategory(name, contents)}
    except:
        raise CustomException(status_code=200,
                              message={"lawName": name.strip(),
                                       "contents": "더 이상 시행되지 않는 법입니다.",
                                       "category": "OTHER"})

@app.exception_handler(CustomException)
async def custom_exception_handler(request, exc: CustomException):
    return JSONResponse(status_code=exc.status_code, content = exc.message)