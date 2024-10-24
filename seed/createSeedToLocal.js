import AWS from "aws-sdk"

const environment = process.argv[2];
const dynamoDB = environment === "local" ?  new AWS.DynamoDB.DocumentClient({
    region: 'localhost',
    endpoint: 'http://localhost:8086',
    credentials: {
        accessKeyId: 'dummy',
        secretAccessKey: 'dummy',
    },
}): new AWS.DynamoDB.DocumentClient({
    region: 'ap-northeast-1',
})

// データを流し込む関数
async function seedData() {

    for (const seed of seedDataList) {
        const params = {
            TableName: `information_table_${environment}`,
            Item: seed
        };
        try {
            await dynamoDB.put(params).promise();
            console.log('Data seeded successfully');
        } catch (error) {
            console.error('Error seeding data:', error);
        }
    }

}

const environment = process.argv[2];

const seedDataList = [
    {category: 'FAQ', id: 0 ,faqCategoryName: 'データ準備', answer: `${environment}Answer0`, question: `${environment}Question0`, answerCreateAt: '2023-10-06T21:34:56+09:00', questionCreateAt: '2023-10-06T21:34:56+09:00'},
    {category: 'FAQ', id: 1 ,faqCategoryName: '3D表示', answer: `${environment}Answer1`, question: `${environment}Question1`, answerCreateAt: '2023-10-07T21:34:56+09:00', questionCreateAt: '2023-10-07T21:34:56+09:00'},
    {category: 'FAQ', id: 2 ,faqCategoryName: 'その他', answer: `${environment}Answer2`, question: `${environment}Question2`, answerCreateAt: '2023-10-08T21:34:56+09:00', questionCreateAt: '2023-10-08T21:34:56+09:00'},
    {category: 'FAQ', id: 3 ,faqCategoryName: 'その他', answer: `${environment}Answer3`, question: `${environment}Question3`, answerCreateAt: '2023-10-09T21:34:56+09:00', questionCreateAt: '2023-10-09T21:34:56+09:00'},
    {category: 'FAQ', id: 4 ,faqCategoryName: '3D表示', answer: `${environment}Answer4`, question: `${environment}Question4`, answerCreateAt: '2023-10-10T21:34:56+09:00', questionCreateAt: '2023-10-10T21:34:56+09:00'},
    {category: 'FAQ', id: 5 ,faqCategoryName: 'データ準備', answer: `${environment}Answer5`, question: `${environment}Question5`, answerCreateAt: '2023-10-11T21:34:56+09:00', questionCreateAt: '2023-10-11T21:34:56+09:00'},
    {category: 'NOTICE', id: 0 ,noticeTitle: `${environment}アップデート`, noticeTag: 'ver0', noticeDescription: 'アップデートしたよ0', noticeCreateAt: '2023-10-11T21:34:56+09:00'},
    {category: 'NOTICE', id: 1 ,noticeTitle: `${environment}アップデート`, noticeTag: 'ver1', noticeDescription: 'アップデートしたよ1', noticeCreateAt: '2023-10-12T21:34:56+09:00'},
    {category: 'NOTICE', id: 2 ,noticeTitle: `${environment}アップデート`, noticeTag: 'ver2', noticeDescription: 'アップデートしたよ2', noticeCreateAt: '2023-10-13T21:34:56+09:00'},
    {category: 'NOTICE', id: 3 ,noticeTitle: `${environment}アップデート`, noticeTag: 'ver3', noticeDescription: 'アップデートしたよ3', noticeCreateAt: '2023-10-14T21:34:56+09:00'},
    {category: 'NOTICE', id: 4 ,noticeTitle: `${environment}アップデート`, noticeTag: 'ver4', noticeDescription: 'アップデートしたよ4', noticeCreateAt: '2023-10-15T21:34:56+09:00'},
]

// 関数を実行
seedData();