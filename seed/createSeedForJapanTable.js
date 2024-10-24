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
            TableName: `japan_table_${environment}`,
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

const seedDataList = [
    {category: "Prefecture", mainSk: "北海道", areaOfPrefecture: 83422.27, populationOfPrefecture: 5058193, metropolitan: "札幌市"},
    {category: "City", mainSk: "札幌市", belongPrefecture: "北海道", areaOfPrefecture: 83422.27, populationOfPrefecture: 5058193, metropolitan: "札幌市", areaOfCity: 1121.26, populationOfCity: 1956630},
    {category: "City", mainSk: "釧路市", belongPrefecture: "北海道", areaOfPrefecture: 83422.27, populationOfPrefecture: 5058193, metropolitan: "札幌市", areaOfCity: 1363.26, populationOfCity: 155311},
    {category: "Prefecture", mainSk: "香川県", areaOfPrefecture: 1876.86, populationOfPrefecture: 917613, metropolitan: "高松市"},
    {category: "City", mainSk: "高松市", belongPrefecture: "香川県", areaOfPrefecture: 1876.86, populationOfPrefecture: 917613, metropolitan: "高松市", areaOfCity: 375.54, populationOfCity: 409346},
    {category: "City", mainSk: "坂出市", belongPrefecture: "香川県", areaOfPrefecture: 1876.86, populationOfPrefecture: 917613, metropolitan: "高松市", areaOfCity: 92.49, populationOfCity: 48181},
    {category: "Prefecture", mainSk: "徳島県", areaOfPrefecture: 4147.00, populationOfPrefecture: 685878, metropolitan: "徳島市"},
    {category: "City", mainSk: "徳島市", belongPrefecture: "徳島県", areaOfPrefecture: 4147.00, populationOfPrefecture: 685878, metropolitan: "徳島市", areaOfCity: 191.52, populationOfCity: 245419},
    {category: "City", mainSk: "小松島市", belongPrefecture: "徳島県", areaOfPrefecture: 4147.00, populationOfPrefecture: 685878, metropolitan: "徳島市", areaOfCity: 45.37, populationOfCity: 33920},
    {category: "Prefecture", mainSk: "愛媛県", areaOfPrefecture: 5675.89, populationOfPrefecture: 1276219, metropolitan: "松山市"},
    {category: "City", mainSk: "松山市", belongPrefecture: "愛媛県", areaOfPrefecture: 5675.89, populationOfPrefecture: 1276219, metropolitan: "松山市", areaOfCity: 429.35, populationOfCity: 498928},
    {category: "City", mainSk: "今治市", belongPrefecture: "愛媛県", areaOfPrefecture: 5675.89, populationOfPrefecture: 1276219, metropolitan: "松山市", areaOfCity: 419.21, populationOfCity: 143187},
    {category: "Prefecture", mainSk: "高知県", areaOfPrefecture: 7102.28, populationOfPrefecture: 656390, metropolitan: "高知市"},
    {category: "City", mainSk: "高知市", belongPrefecture: "高知県", areaOfPrefecture: 7102.28, populationOfPrefecture: 656390, metropolitan: "高知市", areaOfCity: 309.00, populationOfCity: 314141},
    {category: "City", mainSk: "土佐市", belongPrefecture: "高知県", areaOfPrefecture: 7102.28, populationOfPrefecture: 656390, metropolitan: "高知市", areaOfCity: 91.50, populationOfCity: 24767},
    {category: "NextToPrefecture", mainSk: "高知県#愛媛県", areaOfPrefecture: 5675.89, populationOfPrefecture: 1276219, metropolitan: "松山市"},
    {category: "NextToPrefecture", mainSk: "高知県#徳島県", areaOfPrefecture: 4147.00, populationOfPrefecture: 685878, metropolitan: "徳島市"},
    {category: "NextToPrefecture", mainSk: "高知県#香川県", areaOfPrefecture: 1876.86, populationOfPrefecture: 917613, metropolitan: "高松市"},
    {category: "NextToPrefecture", mainSk: "愛媛県#高知県", areaOfPrefecture: 7102.28, populationOfPrefecture: 656390, metropolitan: "高知市"},
    {category: "NextToPrefecture", mainSk: "愛媛県#徳島県", areaOfPrefecture: 4147.00, populationOfPrefecture: 685878, metropolitan: "徳島市"},
    {category: "NextToPrefecture", mainSk: "愛媛県#香川県", areaOfPrefecture: 1876.86, populationOfPrefecture: 917613, metropolitan: "高松市"},
    {category: "NextToPrefecture", mainSk: "香川県#愛媛県", areaOfPrefecture: 5675.89, populationOfPrefecture: 1276219, metropolitan: "松山市"},
    {category: "NextToPrefecture", mainSk: "香川県#徳島県", areaOfPrefecture: 4147.00, populationOfPrefecture: 685878, metropolitan: "徳島市"},
    {category: "NextToPrefecture", mainSk: "香川県#高知県", areaOfPrefecture: 7102.28, populationOfPrefecture: 656390, metropolitan: "高知市"},
    {category: "NextToPrefecture", mainSk: "徳島県#愛媛県", areaOfPrefecture: 5675.89, populationOfPrefecture: 1276219, metropolitan: "松山市"},
    {category: "NextToPrefecture", mainSk: "徳島県#高知県", areaOfPrefecture: 7102.28, populationOfPrefecture: 656390, metropolitan: "高知市"},
    {category: "NextToPrefecture", mainSk: "徳島県#香川県", areaOfPrefecture: 1876.86, populationOfPrefecture: 917613, metropolitan: "高松市"},
]

// 関数を実行
seedData();