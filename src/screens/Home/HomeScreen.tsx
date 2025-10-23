import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Dimensions,
  Image,
  ScrollView,
  Alert,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { MatchUser } from '../../types';

const { width, height } = Dimensions.get('window');
const CARD_WIDTH = width - 40;
const CARD_HEIGHT = height * 0.65;

const HomeScreen: React.FC = () => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [dailyMatches, setDailyMatches] = useState<MatchUser[]>([]);
  const [starPredictions, setStarPredictions] = useState(5);
  const [showStarRecommendation, setShowStarRecommendation] = useState(false);

  useEffect(() => {
    loadDailyMatches();
  }, []);

  const loadDailyMatches = () => {
    // 模拟加载每日推荐用户
    const mockUsers: MatchUser[] = [
      {
        uid: '87654321',
        nickname: '星辰小仙女',
        avatar: '',
        signature: '愿所有美好都如期而至 ✨',
        birthday: '1998-05-20',
        constellation: '金牛座',
        interests: ['阅读', '旅行', '摄影', '音乐'],
        education: {
          level: '硕士',
          school: '清华大学',
          major: '计算机科学',
          verified: true,
        },
        work: {
          company: '字节跳动',
          position: '产品经理',
          verified: true,
        },
        personalInfo: {
          height: 165,
          hometown: '北京',
          family: '独生女',
          lifestyle: '规律作息，热爱运动',
          idealPartner: '幽默有趣，有上进心',
        },
        photos: [],
        starPower: 85,
        isVerified: true,
        createdAt: '2024-01-15',
        lastActive: '2024-01-20',
        matchScore: 92,
        distance: 2.5,
        commonInterests: ['阅读', '旅行'],
      },
      {
        uid: '76543210',
        nickname: '追光少年',
        avatar: '',
        signature: '生活不止眼前的苟且，还有诗和远方',
        birthday: '1996-08-15',
        constellation: '狮子座',
        interests: ['健身', '电影', '编程', '美食'],
        education: {
          level: '本科',
          school: '北京大学',
          major: '软件工程',
          verified: true,
        },
        work: {
          company: '腾讯',
          position: '软件工程师',
          verified: true,
        },
        personalInfo: {
          height: 178,
          hometown: '上海',
          family: '家庭和睦',
          lifestyle: '热爱运动，喜欢探索',
          idealPartner: '善良温柔，有共同话题',
        },
        photos: [],
        starPower: 78,
        isVerified: true,
        createdAt: '2024-01-10',
        lastActive: '2024-01-20',
        matchScore: 88,
        distance: 1.2,
        commonInterests: ['电影', '美食'],
      },
    ];
    setDailyMatches(mockUsers);
  };

  const handleLike = () => {
    if (currentIndex < dailyMatches.length) {
      const user = dailyMatches[currentIndex];
      Alert.alert(
        '点赞成功！',
        `你对 ${user.nickname} 表示了兴趣，如果对方也喜欢你，你们就可以开始聊天了！`,
        [{ text: '确定', onPress: nextCard }]
      );
    }
  };

  const handleDislike = () => {
    nextCard();
  };

  const nextCard = () => {
    if (currentIndex < dailyMatches.length - 1) {
      setCurrentIndex(currentIndex + 1);
    } else {
      Alert.alert('今日推荐已看完', '明天再来看看新的推荐吧！');
    }
  };

  const handleStarPrediction = () => {
    if (starPredictions <= 0) {
      Alert.alert('今日星盘推荐已用完', '明天再来试试吧！');
      return;
    }

    setStarPredictions(starPredictions - 1);
    setShowStarRecommendation(true);

    // 模拟星盘推荐
    const predictions = [
      '今日你的桃花运旺盛，适合主动出击寻找真爱',
      '水星逆行期间，建议多关注内心感受',
      '金星进入你的感情宫，有机会遇到心仪的人',
      '今日适合与火象星座的人交流',
      '月亮在你的社交宫，朋友可能为你介绍对象',
    ];

    const randomPrediction = predictions[Math.floor(Math.random() * predictions.length)];
    
    setTimeout(() => {
      Alert.alert(
        '星盘预测',
        randomPrediction,
        [
          {
            text: '开始匹配',
            onPress: () => {
              setShowStarRecommendation(false);
              Alert.alert('匹配成功！', '已为你匹配到一位神秘的星盘朋友，快去聊天吧！');
            }
          }
        ]
      );
    }, 2000);
  };

  const renderUserCard = (user: MatchUser) => (
    <View style={styles.card}>
      <LinearGradient
        colors={['#16213e', '#1a1a2e']}
        style={styles.cardGradient}
      >
        {/* 用户头像区域 */}
        <View style={styles.avatarSection}>
          <View style={styles.avatarContainer}>
            <View style={styles.avatar}>
              <Text style={styles.avatarText}>
                {user.nickname.charAt(0)}
              </Text>
            </View>
            {user.isVerified && (
              <View style={styles.verifiedBadge}>
                <Icon name="verified" size={16} color="#ffd700" />
              </View>
            )}
          </View>
          <View style={styles.matchScore}>
            <Text style={styles.matchScoreText}>{user.matchScore}%</Text>
            <Text style={styles.matchLabel}>匹配度</Text>
          </View>
        </View>

        {/* 用户信息 */}
        <View style={styles.userInfo}>
          <Text style={styles.nickname}>{user.nickname}</Text>
          <Text style={styles.signature}>{user.signature}</Text>
          
          <View style={styles.basicInfo}>
            <View style={styles.infoItem}>
              <Icon name="cake" size={16} color="#8892b0" />
              <Text style={styles.infoText}>{user.constellation}</Text>
            </View>
            <View style={styles.infoItem}>
              <Icon name="school" size={16} color="#8892b0" />
              <Text style={styles.infoText}>{user.education.school}</Text>
            </View>
            <View style={styles.infoItem}>
              <Icon name="work" size={16} color="#8892b0" />
              <Text style={styles.infoText}>{user.work.company}</Text>
            </View>
            {user.distance && (
              <View style={styles.infoItem}>
                <Icon name="location-on" size={16} color="#8892b0" />
                <Text style={styles.infoText}>{user.distance}km</Text>
              </View>
            )}
          </View>

          {/* 共同兴趣 */}
          <View style={styles.interestsSection}>
            <Text style={styles.sectionTitle}>共同兴趣</Text>
            <View style={styles.interests}>
              {user.commonInterests.map((interest, index) => (
                <View key={index} style={styles.interestTag}>
                  <Text style={styles.interestText}>{interest}</Text>
                </View>
              ))}
            </View>
          </View>

          {/* 星运值 */}
          <View style={styles.starPowerSection}>
            <Text style={styles.sectionTitle}>星运值</Text>
            <View style={styles.starPowerBar}>
              <View style={[styles.starPowerFill, { width: `${user.starPower}%` }]} />
              <Text style={styles.starPowerText}>{user.starPower}</Text>
            </View>
          </View>
        </View>
      </LinearGradient>
    </View>
  );

  return (
    <LinearGradient colors={['#0f0f23', '#1a1a2e']} style={styles.container}>
      {/* 头部 */}
      <View style={styles.header}>
        <Text style={styles.title}>今日推荐</Text>
        <View style={styles.headerRight}>
          <TouchableOpacity
            style={styles.starButton}
            onPress={handleStarPrediction}
          >
            <Icon name="auto-awesome" size={20} color="#ffd700" />
            <Text style={styles.starButtonText}>星盘推荐({starPredictions})</Text>
          </TouchableOpacity>
        </View>
      </View>

      {/* 推荐卡片 */}
      <View style={styles.cardContainer}>
        {showStarRecommendation ? (
          <View style={styles.starPredictionContainer}>
            <LinearGradient
              colors={['#16213e', '#1a1a2e']}
              style={styles.starPredictionCard}
            >
              <Text style={styles.starPredictionTitle}>星盘推荐中...</Text>
              <View style={styles.starAnimation}>
                <Text style={styles.starAnimationText}>✨🔮✨</Text>
              </View>
              <Text style={styles.starPredictionSubtitle}>正在为你寻找命中注定的那个人</Text>
            </LinearGradient>
          </View>
        ) : dailyMatches.length > 0 && currentIndex < dailyMatches.length ? (
          renderUserCard(dailyMatches[currentIndex])
        ) : (
          <View style={styles.emptyContainer}>
            <Text style={styles.emptyText}>今日推荐已看完</Text>
            <Text style={styles.emptySubtext}>明天再来看看新的推荐吧！</Text>
          </View>
        )}
      </View>

      {/* 操作按钮 */}
      {!showStarRecommendation && dailyMatches.length > 0 && currentIndex < dailyMatches.length && (
        <View style={styles.actionButtons}>
          <TouchableOpacity
            style={[styles.actionButton, styles.dislikeButton]}
            onPress={handleDislike}
          >
            <Icon name="close" size={30} color="#ff6b6b" />
          </TouchableOpacity>
          
          <TouchableOpacity
            style={[styles.actionButton, styles.likeButton]}
            onPress={handleLike}
          >
            <Icon name="favorite" size={30} color="#ffd700" />
          </TouchableOpacity>
        </View>
      )}

      {/* 进度指示器 */}
      <View style={styles.progressContainer}>
        <Text style={styles.progressText}>
          {currentIndex + 1} / {Math.max(dailyMatches.length, 21)}
        </Text>
      </View>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingTop: 50,
    paddingBottom: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  headerRight: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  starButton: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#16213e',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 15,
  },
  starButtonText: {
    fontSize: 12,
    color: '#ffd700',
    marginLeft: 4,
    fontWeight: '600',
  },
  cardContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 20,
  },
  card: {
    width: CARD_WIDTH,
    height: CARD_HEIGHT,
    borderRadius: 20,
    overflow: 'hidden',
    elevation: 8,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 8,
  },
  cardGradient: {
    flex: 1,
    padding: 20,
  },
  avatarSection: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  avatarContainer: {
    position: 'relative',
  },
  avatar: {
    width: 80,
    height: 80,
    borderRadius: 40,
    backgroundColor: '#ffd700',
    justifyContent: 'center',
    alignItems: 'center',
  },
  avatarText: {
    fontSize: 32,
    fontWeight: 'bold',
    color: '#1a1a2e',
  },
  verifiedBadge: {
    position: 'absolute',
    bottom: -2,
    right: -2,
    backgroundColor: '#16213e',
    borderRadius: 12,
    padding: 2,
  },
  matchScore: {
    alignItems: 'center',
  },
  matchScoreText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ffd700',
  },
  matchLabel: {
    fontSize: 12,
    color: '#8892b0',
  },
  userInfo: {
    flex: 1,
  },
  nickname: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#ccd6f6',
    marginBottom: 8,
  },
  signature: {
    fontSize: 16,
    color: '#8892b0',
    marginBottom: 20,
    lineHeight: 22,
  },
  basicInfo: {
    marginBottom: 20,
  },
  infoItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 8,
  },
  infoText: {
    fontSize: 14,
    color: '#ccd6f6',
    marginLeft: 8,
  },
  interestsSection: {
    marginBottom: 20,
  },
  sectionTitle: {
    fontSize: 16,
    fontWeight: '600',
    color: '#ccd6f6',
    marginBottom: 10,
  },
  interests: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  interestTag: {
    backgroundColor: '#ffd700',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 15,
    marginRight: 8,
    marginBottom: 8,
  },
  interestText: {
    fontSize: 12,
    color: '#1a1a2e',
    fontWeight: '600',
  },
  starPowerSection: {
    marginBottom: 20,
  },
  starPowerBar: {
    height: 8,
    backgroundColor: '#16213e',
    borderRadius: 4,
    position: 'relative',
    justifyContent: 'center',
  },
  starPowerFill: {
    height: '100%',
    backgroundColor: '#ffd700',
    borderRadius: 4,
  },
  starPowerText: {
    position: 'absolute',
    right: 8,
    fontSize: 12,
    color: '#1a1a2e',
    fontWeight: 'bold',
  },
  actionButtons: {
    flexDirection: 'row',
    justifyContent: 'center',
    paddingHorizontal: 60,
    paddingBottom: 30,
  },
  actionButton: {
    width: 60,
    height: 60,
    borderRadius: 30,
    justifyContent: 'center',
    alignItems: 'center',
    marginHorizontal: 20,
    elevation: 4,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.2,
    shadowRadius: 4,
  },
  dislikeButton: {
    backgroundColor: '#2a2a3e',
  },
  likeButton: {
    backgroundColor: '#2a2a3e',
  },
  progressContainer: {
    alignItems: 'center',
    paddingBottom: 20,
  },
  progressText: {
    fontSize: 14,
    color: '#8892b0',
  },
  emptyContainer: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  emptyText: {
    fontSize: 20,
    color: '#ccd6f6',
    marginBottom: 8,
  },
  emptySubtext: {
    fontSize: 14,
    color: '#8892b0',
  },
  starPredictionContainer: {
    width: CARD_WIDTH,
    height: CARD_HEIGHT,
  },
  starPredictionCard: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 20,
  },
  starPredictionTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ffd700',
    marginBottom: 30,
  },
  starAnimation: {
    marginBottom: 30,
  },
  starAnimationText: {
    fontSize: 48,
  },
  starPredictionSubtitle: {
    fontSize: 16,
    color: '#8892b0',
    textAlign: 'center',
  },
});

export default HomeScreen;