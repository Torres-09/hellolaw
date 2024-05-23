import requests
from bs4 import BeautifulSoup

baseUrl = "https://law.go.kr/%EB%B2%95%EB%A0%B9/"

def crawlLawContents(url):
    try:
        # 페이지 요청
        response = requests.get(url)
        response.raise_for_status()  # 상태 코드가 200이 아닌 경우 예외 발생

        # BeautifulSoup을 사용하여 HTML 파싱
        soup = BeautifulSoup(response.text, 'html.parser')

        # lawService 프레임 찾기
        frame = soup.find('iframe', id='lawService')
        if frame:
            frame_url = frame['src']
            # 프레임 URL이 상대 경로일 경우 절대 경로로 변환
            if frame_url.startswith('/'):
                frame_url = 'https://law.go.kr' + frame_url

            # 프레임의 콘텐츠 요청
            frame_response = requests.get(frame_url)
            frame_response.raise_for_status()

            frame_soup = BeautifulSoup(frame_response.text, 'html.parser')
            law_content = frame_soup.find(class_='lawcon')

            if law_content:
                return law_content.get_text(strip=True)
            else:
                return "더 이상 시행되지 않는 법입니다."
        else:
            return "더 이상 시행되지 않는 법입니다."
    except Exception as e:
        print("Error: ", str(e))
        return str(e)

def getLawContents(name):
    path = name.split()
    url = ""
    name = ""
    for p in path:
        url = url + p + "/"
        name = name + p + " "
    url = baseUrl + url
    return name.strip(), crawlLawContents(url)
