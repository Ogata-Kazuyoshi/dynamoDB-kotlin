const AWS = require('aws-sdk');

// DynamoDB Localのエンドポイントを設定
const dynamoDB = new AWS.DynamoDB.DocumentClient({
    region: 'localhost',
    endpoint: 'http://localhost:8086'
});

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
    {category: 'FAQ', id: 0 ,faqCategoryName: 'データ準備', answer: `${environment}Answer0`, question: `${environment}Question0`, createAtAnswer: '2023-10-06T21:34:56+09:00', createAtQuestion: '2023-10-06T21:34:56+09:00'},
    {category: 'FAQ', id: 1 ,faqCategoryName: '3D表示', answer: `${environment}Answer1`, question: `${environment}Question1`, createAtAnswer: '2023-10-07T21:34:56+09:00', createAtQuestion: '2023-10-07T21:34:56+09:00'},
    {category: 'FAQ', id: 2 ,faqCategoryName: 'その他', answer: `${environment}Answer2`, question: `${environment}Question2`, createAtAnswer: '2023-10-08T21:34:56+09:00', createAtQuestion: '2023-10-08T21:34:56+09:00'},
    {category: 'FAQ', id: 3 ,faqCategoryName: 'その他', answer: `${environment}Answer3`, question: `${environment}Question3`, createAtAnswer: '2023-10-09T21:34:56+09:00', createAtQuestion: '2023-10-09T21:34:56+09:00'},
    {category: 'FAQ', id: 4 ,faqCategoryName: '3D表示', answer: `${environment}Answer4`, question: `${environment}Question4`, createAtAnswer: '2023-10-10T21:34:56+09:00', createAtQuestion: '2023-10-10T21:34:56+09:00'},
    {category: 'FAQ', id: 5 ,faqCategoryName: 'データ準備', answer: `${environment}Answer5`, question: `${environment}Question5`, createAtAnswer: '2023-10-11T21:34:56+09:00', createAtQuestion: '2023-10-11T21:34:56+09:00'},
]

// 関数を実行
seedData();