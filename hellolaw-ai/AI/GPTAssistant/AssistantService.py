import time

from pydantic import BaseModel

from AI.GPTAssistant.AssistantAPI import AssistantAPI


class Answer(BaseModel):
    text: str


def getAnswer(client, text: str):
    if not client: return Answer(text="Error")
    api = AssistantAPI(client)

    assistant = api.getAssistant()
    thread = api.createThread()
    api.createMessage(thread, text)

    run = api.createRun(assistant, thread)
    cnt = 0
    while run.status != "completed":
        time.sleep(1)
        cnt += 1
        if cnt > 20 : break
        print("waiting..." + str(cnt))
        run = api.getRun(thread).data[0]

    answer = api.getMessage(thread).data[0].content[0].text.value

    if not api.deleteThread(thread).deleted or not answer:
        return Answer(text="Error")
    return Answer(text=answer)
