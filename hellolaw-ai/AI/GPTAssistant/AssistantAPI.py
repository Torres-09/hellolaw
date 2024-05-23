ASSISTANT_ID = "asst_SyGXUjDhZTQZqWg3gRgvqdY9"

class AssistantAPI:
    def __init__(self, client):
        self.client = client

    def getAssistant(self):
        return self.client.beta.assistants.retrieve(assistant_id=ASSISTANT_ID)

    def createThread(self):
        return self.client.beta.threads.create()

    def createRun(self, assistant, thread):
        return self.client.beta.threads.runs.create(
            thread_id=thread.id,
            assistant_id=assistant.id,
        )

    def deleteThread(self, thread):
        return self.client.beta.threads.delete(thread.id)

    def createMessage(self, thread, msg):
        return self.client.beta.threads.messages.create(
            thread.id,
            role="user",
            content=msg,
        )

    def getMessage(self, thread):
        return self.client.beta.threads.messages.list(thread.id)

    def getRun(self, thread):
        return self.client.beta.threads.runs.list(thread.id)
