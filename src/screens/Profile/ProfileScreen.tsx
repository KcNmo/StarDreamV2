import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  Alert,
  Modal,
  TextInput,
} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import Icon from 'react-native-vector-icons/MaterialIcons';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { useNavigation } from '@react-navigation/native';
import { User, Moment } from '../../types';

const ProfileScreen: React.FC = () => {
  const navigation = useNavigation();
  const [user, setUser] = useState<User | null>(null);
  const [userMoments, setUserMoments] = useState<Moment[]>([]);
  const [showEditModal, setShowEditModal] = useState(false);
  const [editField, setEditField] = useState<string>('');
  const [editValue, setEditValue] = useState<string>('');

  useEffect(() => {
    loadUserProfile();
    loadUserMoments();
  }, []);

  const loadUserProfile = async () => {
    try {
      const userInfo = await AsyncStorage.getItem('userInfo');
      if (userInfo) {
        const parsedUser = JSON.parse(userInfo);
        // 模拟完整用户信息
        const fullUser: User = {
          uid: parsedUser.uid || '12345678',
          phone: '138****8000',
          nickname: parsedUser.nickname || '星辰用户',
          avatar: parsedUser.avatar || '',
          signature: '愿所有美好都如期而至 ✨',
          birthday: '1995-06-15',
          constellation: '双子座',
          interests: ['编程', '音乐', '旅行', '摄影'],
          education: {
            level: '本科',
            school: '某某大学',
            major: '计算机科学',
            verified: true,
          },
          work: {
            company: '某某科技',
            position: '软件工程师',
            verified: true,
          },
          personalInfo: {
            height: 175,
            hometown: '北京',
            family: '独生子',
            lifestyle: '规律生活，热爱运动',
            idealPartner: '温柔善良，有共同话题',
          },
          photos: [],
          starPower: 88,
          isVerified: true,
          createdAt: '2024-01-01',
          lastActive: '2024-01-20',
        };
        setUser(fullUser);
      }
    } catch (error) {
      console.error('加载用户信息失败:', error);
    }
  };

  const loadUserMoments = () => {
    // 模拟用户动态
    const mockMoments: Moment[] = [
      {
        id: '1',
        userId: '12345678',
        user: {} as User, // 简化处理
        content: '今天学习了React Native，感觉很有趣！准备用它做一个社交APP 📱',
        images: [],
        type: 'normal',
        visibility: 'public_sea',
        tags: ['学习', '技术'],
        likes: ['87654321', '76543210'],
        comments: [],
        createdAt: '2024-01-20T14:30:00Z',
      },
      {
        id: '2',
        userId: '12345678',
        user: {} as User,
        content: '周末去爬山了，风景真美！分享几张照片给大家 🏔️',
        images: [],
        type: 'normal',
        visibility: 'private_circle',
        tags: ['旅行', '户外'],
        likes: ['87654321'],
        comments: [],
        createdAt: '2024-01-19T16:20:00Z',
      },
    ];
    setUserMoments(mockMoments);
  };

  const handleLogout = () => {
    Alert.alert(
      '确认退出',
      '确定要退出登录吗？',
      [
        { text: '取消', style: 'cancel' },
        {
          text: '退出',
          style: 'destructive',
          onPress: async () => {
            try {
              await AsyncStorage.removeItem('userToken');
              await AsyncStorage.removeItem('userInfo');
              navigation.reset({
                index: 0,
                routes: [{ name: 'Auth' as never }]
              });
            } catch (error) {
              console.error('退出登录失败:', error);
            }
          }
        }
      ]
    );
  };

  const handleEditProfile = (field: string, currentValue: string) => {
    setEditField(field);
    setEditValue(currentValue);
    setShowEditModal(true);
  };

  const saveEdit = () => {
    if (!user) return;

    const updatedUser = { ...user };
    switch (editField) {
      case 'nickname':
        updatedUser.nickname = editValue;
        break;
      case 'signature':
        updatedUser.signature = editValue;
        break;
      case 'hometown':
        updatedUser.personalInfo.hometown = editValue;
        break;
      case 'lifestyle':
        updatedUser.personalInfo.lifestyle = editValue;
        break;
      case 'idealPartner':
        updatedUser.personalInfo.idealPartner = editValue;
        break;
    }

    setUser(updatedUser);
    setShowEditModal(false);
    Alert.alert('保存成功', '个人信息已更新');
  };

  const renderStarPower = (starPower: number) => {
    const stars = Math.floor(starPower / 20);
    const halfStar = (starPower % 20) >= 10;
    const emptyStars = 5 - stars - (halfStar ? 1 : 0);

    return (
      <View style={styles.starPowerContainer}>
        {Array(stars).fill(0).map((_, i) => (
          <Text key={`full-${i}`} style={styles.starIcon}>⭐</Text>
        ))}
        {halfStar && <Text style={styles.starIcon}>✨</Text>}
        {Array(emptyStars).fill(0).map((_, i) => (
          <Text key={`empty-${i}`} style={styles.emptyStarIcon}>☆</Text>
        ))}
      </View>
    );
  };

  if (!user) {
    return (
      <LinearGradient colors={['#0f0f23', '#1a1a2e']} style={styles.container}>
        <View style={styles.loadingContainer}>
          <Text style={styles.loadingText}>加载中...</Text>
        </View>
      </LinearGradient>
    );
  }

  return (
    <LinearGradient colors={['#0f0f23', '#1a1a2e']} style={styles.container}>
      <ScrollView showsVerticalScrollIndicator={false}>
        {/* 头部 */}
        <View style={styles.header}>
          <Text style={styles.title}>个人主页</Text>
          <TouchableOpacity
            style={styles.settingsButton}
            onPress={() => Alert.alert('设置', '设置功能开发中')}
          >
            <Icon name="settings" size={24} color="#ccd6f6" />
          </TouchableOpacity>
        </View>

        {/* 用户卡片 */}
        <View style={styles.userCard}>
          <LinearGradient
            colors={['#16213e', '#1a1a2e']}
            style={styles.userCardGradient}
          >
            {/* 基础信息 */}
            <View style={styles.basicInfo}>
              <View style={styles.avatarSection}>
                <View style={styles.avatar}>
                  <Text style={styles.avatarText}>{user.nickname.charAt(0)}</Text>
                </View>
                {user.isVerified && (
                  <View style={styles.verifiedBadge}>
                    <Icon name="verified" size={16} color="#ffd700" />
                  </View>
                )}
              </View>

              <View style={styles.userInfo}>
                <View style={styles.nameRow}>
                  <Text style={styles.nickname}>{user.nickname}</Text>
                  <TouchableOpacity
                    style={styles.editButton}
                    onPress={() => handleEditProfile('nickname', user.nickname)}
                  >
                    <Icon name="edit" size={16} color="#8892b0" />
                  </TouchableOpacity>
                </View>
                <Text style={styles.uid}>UID: {user.uid}</Text>
                <TouchableOpacity
                  style={styles.signatureContainer}
                  onPress={() => handleEditProfile('signature', user.signature)}
                >
                  <Text style={styles.signature}>{user.signature}</Text>
                  <Icon name="edit" size={14} color="#8892b0" />
                </TouchableOpacity>
              </View>
            </View>

            {/* 星运值 */}
            <View style={styles.starPowerSection}>
              <Text style={styles.sectionTitle}>星运值</Text>
              <View style={styles.starPowerDisplay}>
                {renderStarPower(user.starPower)}
                <Text style={styles.starPowerText}>{user.starPower}/100</Text>
              </View>
            </View>
          </LinearGradient>
        </View>

        {/* 详细信息 */}
        <View style={styles.detailsCard}>
          <LinearGradient
            colors={['#16213e', '#1a1a2e']}
            style={styles.detailsGradient}
          >
            <Text style={styles.sectionTitle}>详细信息</Text>

            <View style={styles.infoGrid}>
              <View style={styles.infoItem}>
                <Icon name="cake" size={20} color="#ffd700" />
                <Text style={styles.infoLabel}>星座</Text>
                <Text style={styles.infoValue}>{user.constellation}</Text>
              </View>

              <View style={styles.infoItem}>
                <Icon name="school" size={20} color="#ffd700" />
                <Text style={styles.infoLabel}>学历</Text>
                <Text style={styles.infoValue}>
                  {user.education.level} · {user.education.school}
                  {user.education.verified && <Icon name="verified" size={12} color="#ffd700" />}
                </Text>
              </View>

              <View style={styles.infoItem}>
                <Icon name="work" size={20} color="#ffd700" />
                <Text style={styles.infoLabel}>工作</Text>
                <Text style={styles.infoValue}>
                  {user.work.position} · {user.work.company}
                  {user.work.verified && <Icon name="verified" size={12} color="#ffd700" />}
                </Text>
              </View>

              <TouchableOpacity
                style={styles.infoItem}
                onPress={() => handleEditProfile('hometown', user.personalInfo.hometown)}
              >
                <Icon name="location-on" size={20} color="#ffd700" />
                <Text style={styles.infoLabel}>家乡</Text>
                <Text style={styles.infoValue}>{user.personalInfo.hometown}</Text>
                <Icon name="edit" size={14} color="#8892b0" />
              </TouchableOpacity>

              <TouchableOpacity
                style={styles.infoItem}
                onPress={() => handleEditProfile('lifestyle', user.personalInfo.lifestyle)}
              >
                <Icon name="favorite" size={20} color="#ffd700" />
                <Text style={styles.infoLabel}>生活方式</Text>
                <Text style={styles.infoValue}>{user.personalInfo.lifestyle}</Text>
                <Icon name="edit" size={14} color="#8892b0" />
              </TouchableOpacity>

              <TouchableOpacity
                style={styles.infoItem}
                onPress={() => handleEditProfile('idealPartner', user.personalInfo.idealPartner)}
              >
                <Icon name="person-search" size={20} color="#ffd700" />
                <Text style={styles.infoLabel}>理想的Ta</Text>
                <Text style={styles.infoValue}>{user.personalInfo.idealPartner}</Text>
                <Icon name="edit" size={14} color="#8892b0" />
              </TouchableOpacity>
            </View>

            {/* 兴趣标签 */}
            <View style={styles.interestsSection}>
              <Text style={styles.sectionTitle}>兴趣爱好</Text>
              <View style={styles.interests}>
                {user.interests.map((interest, index) => (
                  <View key={index} style={styles.interestTag}>
                    <Text style={styles.interestText}>{interest}</Text>
                  </View>
                ))}
              </View>
            </View>
          </LinearGradient>
        </View>

        {/* 我的动态 */}
        <View style={styles.momentsCard}>
          <LinearGradient
            colors={['#16213e', '#1a1a2e']}
            style={styles.momentsGradient}
          >
            <View style={styles.momentsHeader}>
              <Text style={styles.sectionTitle}>我的动态</Text>
              <Text style={styles.momentsCount}>({userMoments.length})</Text>
            </View>

            {userMoments.length > 0 ? (
              userMoments.slice(0, 3).map((moment) => (
                <View key={moment.id} style={styles.momentItem}>
                  <Text style={styles.momentContent} numberOfLines={2}>
                    {moment.content}
                  </Text>
                  <View style={styles.momentMeta}>
                    <Text style={styles.momentTime}>
                      {new Date(moment.createdAt).toLocaleDateString()}
                    </Text>
                    <View style={styles.momentStats}>
                      <Icon name="favorite" size={12} color="#ff6b6b" />
                      <Text style={styles.momentStatsText}>{moment.likes.length}</Text>
                    </View>
                  </View>
                </View>
              ))
            ) : (
              <Text style={styles.noMomentsText}>还没有发布动态</Text>
            )}

            {userMoments.length > 3 && (
              <TouchableOpacity style={styles.viewAllButton}>
                <Text style={styles.viewAllText}>查看全部动态</Text>
              </TouchableOpacity>
            )}
          </LinearGradient>
        </View>

        {/* 功能按钮 */}
        <View style={styles.actionsCard}>
          <LinearGradient
            colors={['#16213e', '#1a1a2e']}
            style={styles.actionsGradient}
          >
            <TouchableOpacity style={styles.actionItem}>
              <Icon name="photo-library" size={24} color="#ffd700" />
              <Text style={styles.actionText}>我的相册</Text>
              <Icon name="chevron-right" size={20} color="#8892b0" />
            </TouchableOpacity>

            <TouchableOpacity style={styles.actionItem}>
              <Icon name="verified-user" size={24} color="#ffd700" />
              <Text style={styles.actionText}>认证中心</Text>
              <Icon name="chevron-right" size={20} color="#8892b0" />
            </TouchableOpacity>

            <TouchableOpacity style={styles.actionItem}>
              <Icon name="privacy-tip" size={24} color="#ffd700" />
              <Text style={styles.actionText}>隐私设置</Text>
              <Icon name="chevron-right" size={20} color="#8892b0" />
            </TouchableOpacity>

            <TouchableOpacity style={styles.actionItem}>
              <Icon name="help" size={24} color="#ffd700" />
              <Text style={styles.actionText}>帮助中心</Text>
              <Icon name="chevron-right" size={20} color="#8892b0" />
            </TouchableOpacity>

            <TouchableOpacity
              style={[styles.actionItem, styles.logoutItem]}
              onPress={handleLogout}
            >
              <Icon name="logout" size={24} color="#ff6b6b" />
              <Text style={[styles.actionText, styles.logoutText]}>退出登录</Text>
              <Icon name="chevron-right" size={20} color="#8892b0" />
            </TouchableOpacity>
          </LinearGradient>
        </View>
      </ScrollView>

      {/* 编辑模态框 */}
      <Modal
        visible={showEditModal}
        animationType="slide"
        transparent={true}
        onRequestClose={() => setShowEditModal(false)}
      >
        <View style={styles.modalOverlay}>
          <LinearGradient
            colors={['#16213e', '#1a1a2e']}
            style={styles.modalContent}
          >
            <View style={styles.modalHeader}>
              <TouchableOpacity onPress={() => setShowEditModal(false)}>
                <Text style={styles.modalCancel}>取消</Text>
              </TouchableOpacity>
              <Text style={styles.modalTitle}>编辑信息</Text>
              <TouchableOpacity onPress={saveEdit}>
                <Text style={styles.modalConfirm}>保存</Text>
              </TouchableOpacity>
            </View>

            <View style={styles.modalBody}>
              <TextInput
                style={styles.editInput}
                value={editValue}
                onChangeText={setEditValue}
                placeholder="请输入内容"
                placeholderTextColor="#8892b0"
                multiline={editField === 'signature' || editField === 'lifestyle' || editField === 'idealPartner'}
                maxLength={editField === 'nickname' ? 20 : 100}
              />
            </View>
          </LinearGradient>
        </View>
      </Modal>
    </LinearGradient>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingText: {
    fontSize: 16,
    color: '#ccd6f6',
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
  settingsButton: {
    padding: 8,
  },
  userCard: {
    marginHorizontal: 20,
    marginBottom: 16,
    borderRadius: 16,
    overflow: 'hidden',
  },
  userCardGradient: {
    padding: 20,
  },
  basicInfo: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 20,
  },
  avatarSection: {
    position: 'relative',
    marginRight: 16,
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
  userInfo: {
    flex: 1,
  },
  nameRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 4,
  },
  nickname: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ccd6f6',
    marginRight: 8,
  },
  editButton: {
    padding: 4,
  },
  uid: {
    fontSize: 14,
    color: '#8892b0',
    marginBottom: 8,
  },
  signatureContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  signature: {
    fontSize: 14,
    color: '#ccd6f6',
    marginRight: 8,
    flex: 1,
  },
  starPowerSection: {
    borderTopWidth: 1,
    borderTopColor: '#2a2a3e',
    paddingTop: 16,
  },
  sectionTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#ccd6f6',
    marginBottom: 12,
  },
  starPowerDisplay: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  starPowerContainer: {
    flexDirection: 'row',
  },
  starIcon: {
    fontSize: 20,
    marginRight: 2,
  },
  emptyStarIcon: {
    fontSize: 20,
    color: '#8892b0',
    marginRight: 2,
  },
  starPowerText: {
    fontSize: 16,
    color: '#ffd700',
    fontWeight: 'bold',
  },
  detailsCard: {
    marginHorizontal: 20,
    marginBottom: 16,
    borderRadius: 16,
    overflow: 'hidden',
  },
  detailsGradient: {
    padding: 20,
  },
  infoGrid: {
    marginBottom: 20,
  },
  infoItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 16,
  },
  infoLabel: {
    fontSize: 14,
    color: '#8892b0',
    marginLeft: 12,
    minWidth: 60,
  },
  infoValue: {
    fontSize: 14,
    color: '#ccd6f6',
    marginLeft: 12,
    flex: 1,
  },
  interestsSection: {
    borderTopWidth: 1,
    borderTopColor: '#2a2a3e',
    paddingTop: 16,
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
  momentsCard: {
    marginHorizontal: 20,
    marginBottom: 16,
    borderRadius: 16,
    overflow: 'hidden',
  },
  momentsGradient: {
    padding: 20,
  },
  momentsHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 16,
  },
  momentsCount: {
    fontSize: 14,
    color: '#8892b0',
    marginLeft: 8,
  },
  momentItem: {
    marginBottom: 12,
    paddingBottom: 12,
    borderBottomWidth: 1,
    borderBottomColor: '#2a2a3e',
  },
  momentContent: {
    fontSize: 14,
    color: '#ccd6f6',
    lineHeight: 20,
    marginBottom: 8,
  },
  momentMeta: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  momentTime: {
    fontSize: 12,
    color: '#8892b0',
  },
  momentStats: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  momentStatsText: {
    fontSize: 12,
    color: '#8892b0',
    marginLeft: 4,
  },
  noMomentsText: {
    fontSize: 14,
    color: '#8892b0',
    textAlign: 'center',
    paddingVertical: 20,
  },
  viewAllButton: {
    alignItems: 'center',
    paddingTop: 12,
    borderTopWidth: 1,
    borderTopColor: '#2a2a3e',
  },
  viewAllText: {
    fontSize: 14,
    color: '#ffd700',
  },
  actionsCard: {
    marginHorizontal: 20,
    marginBottom: 40,
    borderRadius: 16,
    overflow: 'hidden',
  },
  actionsGradient: {
    padding: 20,
  },
  actionItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#2a2a3e',
  },
  actionText: {
    fontSize: 16,
    color: '#ccd6f6',
    marginLeft: 16,
    flex: 1,
  },
  logoutItem: {
    borderBottomWidth: 0,
  },
  logoutText: {
    color: '#ff6b6b',
  },
  modalOverlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.7)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  modalContent: {
    width: '90%',
    borderRadius: 16,
    maxHeight: '60%',
  },
  modalHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#2a2a3e',
  },
  modalCancel: {
    fontSize: 16,
    color: '#8892b0',
  },
  modalTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#ccd6f6',
  },
  modalConfirm: {
    fontSize: 16,
    color: '#ffd700',
    fontWeight: '600',
  },
  modalBody: {
    padding: 20,
  },
  editInput: {
    fontSize: 16,
    color: '#ccd6f6',
    borderBottomWidth: 1,
    borderBottomColor: '#2a2a3e',
    paddingVertical: 12,
    textAlignVertical: 'top',
    minHeight: 80,
  },
});

export default ProfileScreen;