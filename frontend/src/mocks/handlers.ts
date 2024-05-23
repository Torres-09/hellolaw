import { delay, http, HttpResponse } from 'msw';
const baseUrl = 'http://msw:8080';

export const handlers = [
  http.get(baseUrl + '/auth/oauth2/authorize/kakao', () => {
    return new HttpResponse(null, {
      status: 200,
      statusText: 'Save Cookie',
    });
  }),
  http.get(baseUrl + '/auth/logout', () => {
    return new HttpResponse(null, {
      status: 200,
      statusText: 'Logut Success',
    });
  }),
  http.post(baseUrl + '/api/question', async () => {
    const data = {
      suggestion: '이렇게 하세요',
      precedent: {
        precedentId: 3,
        lawType: '형사',
        precedentSummary: '요약판례임',
        category: '음주운전',
      },
      relatedLaws: ['관세법 제107조', '관세법 제108조', '관세법 제109조'],
    };
    await delay(20000);
    return HttpResponse.json(data, {
      status: 200,
      statusText: 'QuestionSuccess',
    });
  }),
  http.get(baseUrl + '/api/question/history', () => {
    const questions = [
      {
        id: 1,
        summary: '음주운전 2회로 집행유예 기간 중, 무면허 음주운전으로 적발되었습니다.',
        lawType: '형사',
        category: '스토킹',
      },
      {
        id: 2,
        summary: '최근 교통사고를 당해 상당한 피해를 입었습니다',
        lawType: '형사',
        category: '교통사고/음주운전',
      },
    ];
    return HttpResponse.json(questions, {
      status: 200,
      statusText: 'QuestionSuccess',
    });
  }),
  http.delete(baseUrl + '/api/question/v1/:questionId', ({ params }) => {
    const { questionId } = params;

    return HttpResponse.json(null, {
      status: 200,
      statusText: `${questionId} DeleteSuccess`,
    });
  }),
  http.get(baseUrl + '/api/law/ranking', ({ request }) => {
    const url = new URL(request.url);
    const category = url.searchParams.get('category');
    console.log('category', category);
    const datas = [
      {
        rank: 1,
        lawId: 5,
        lawName: '제 1조 5항',
      },
      {
        rank: 2,
        lawId: 11,
        lawName: '제 2조 10항',
      },
      {
        rank: 3,
        lawId: 16,
        lawName: '제 2조 10항',
      },
      {
        rank: 4,
        lawId: 1,
        lawName: '제 2조 10항',
      },
    ];

    return HttpResponse.json(datas, {
      status: 200,
      statusText: ` Success`,
    });
  }),
];
